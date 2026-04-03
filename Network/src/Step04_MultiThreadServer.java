import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Step04_MultiThreadServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("멀티 스레드 서버 시작");
            System.out.println("여러 탭에서 접속해도 멈추지 않고 처리됩니다.");


            while (true) {
                // 클라이언트 접속 대기
                Socket socket = serverSocket.accept();
                // 새로운 스레드 생성 및 실행 (비동기)
                // 메인 스레드는 즉시 다음 accpt()를 호출하러 들어간다.
                Thread thread = new Thread(() -> handleRequest( socket));
                thread.start();

                /**
                 * Context Switching 파악해보기
                 *
                 * 1. thread.start()를 실행하면 OS는 여러 개의 스레드를 번갈아 가며 실행
                 * - CPU는 한 번에 하나의 Thread만 처리 가능 (코어당).
                 * - 만약 스레드가 10개, 코어가 4개라면 OS는 아주 짧은 시간(ms 단위) 동안 1번 스레드 실행하다가 상태 저장하고 2번 스레드로 갈아끼움.
                 * * 2. I/O 블로킹에 의한 발생 (자발적 양보)
                 * - in.readLine() 또는 serverSocket.accept() 시점
                 * 2.1. in.readLine() 호출했는데 클라이언트가 데이터 다 안 보냈으면 데이터를 기다리며 Wait 상태에 빠짐.
                 * 2.2. OS는 이 스레드를 CPU에서 빼버리고(Preemption), 대기 중인 다른 스레드에게 CPU 자원을 할당함.
                 * * 3. Context Switching은 비용이 많이 든다.
                 * - 컨텍스트 스위칭이 일어날 때 TCB(Thread Control Block)에 다음 정보를 저장하고 복구함:
                 * - Program Counter : 다음에 실행해야 할 코드 주소
                 * - Registers : 현재 계산 중이던 값들
                 * - Stack Pointer : 현재 함수 호출 상태
                 * * 스레드가 너무 많아지면 Context Switching 시간이 실제 일하는 시간보다 많아짐.
                 * 배보다 배꼽이 더 큰 상황 -> 스레드 폭주(Thrashing)!
                 */

            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) {
        // 현재 실행 중인 스레드 이름을 출력하여 '동시성' 증명
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] 연결 수락: " + socket.getRemoteSocketAddress());

        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             OutputStream out = socket.getOutputStream()) {

            String line = in.readLine();
            if (line == null) return;

            // GET /user?name=ChaeHun&id=123 HTTP/1.1 형태의 첫 줄 분석
            String[] tokens = line.split(" ");
            String pathWithQuery = tokens[1];

            // Query Parameter 파싱 (Step 03 로직)
            Map<String, String> params = new HashMap<>();
            if (pathWithQuery.contains("?")) {
                String queryString = pathWithQuery.split("\\?")[1];
                String[] pairs = queryString.split("&");
                for (String pair : pairs) {
                    String[] kv = pair.split("=");
                    if (kv.length == 2) params.put(kv[0], kv[1]);
                }
            }

            String name = params.getOrDefault("name", "Guest");
            String id = params.getOrDefault("id", "Unknown");

            // HTTP 응답 생성
            String body = "<h1>Multi-Thread Server Response</h1>" +
                    "<p>Thread Name: <b>" + threadName + "</b></p>" +
                    "<p>Hello, " + name + "! (ID: " + id + ")</p>";

            byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
            String responseHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: " + bodyBytes.length + "\r\n" +
                    "\r\n";

            out.write(responseHeader.getBytes());
            out.write(bodyBytes);
            out.flush();

            System.out.println("[" + threadName + "] 요청 처리 완료.");

        } catch (IOException e) {
            System.err.println("[" + threadName + "] 에러 발생: " + e.getMessage());
        }
    }
}
