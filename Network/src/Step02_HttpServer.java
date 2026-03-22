import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Step02_HttpServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println(" HTTP 서버가 8080에서 대기 중 브라우저로 접속하세요");

            while (true) {
                try(Socket socket = serverSocket.accept()) {
                    // 브라우저가 보내는 메세지 읽기 준비
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("\n--- [HTTP Request Header] ---");

                    String line;

                    // 빈 줄이 나올 때까지 헤더의 끝까지 읽어서 출력
                    while (!(line = br.readLine()).isEmpty()) {
                        System.out.println(line);
                    }

                    // 브라우저에게 응답 보내기 (HTTP 규격에 맞춰서!)
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("HTTP/1.1 200 OK"); // 상태 코드
                    out.println("Content-Type: text/html; charset=UTF-8"); // 컨텐츠 타입
                    out.println(""); // 헤더와 본문 사이의 빈 줄 (필수!)
                    out.println("<h1>Hello from My Java Server!</h1>");
                    out.println("<p>방금 브라우저의 요청을 직접 처리하셨습니다.</p>");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
