import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Step01_Server {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("서버가 8080포트에서 대기 중...");

            while (true) {
                // 2. [OS 레벨] SYN-ACK 전송 (Ack: X+1, Seq: Y) - 자동 발생
                // 클라이언트의 SYN(1번)이 들어오면 서버 OS가 자동으로 수락 신호를 보냅니다.

                // accept()는 클라이언트의 마지막 ACK(3번)가 도착할 때까지 대기하다가,
                // 3-Way Handshake가 완전히 완료된 'Socket' 객체를 반환하며 리턴됩니다.
                Socket socket = serverSocket.accept();
                System.out.println("클라이언트 연결 성공: " + socket.getInetAddress());

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("TCP 연결을 통한 메시지입니다.");

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
