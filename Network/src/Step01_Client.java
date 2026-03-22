import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Step01_Client {
    public static void main(String[] args) {
        // 1. [OS 레벨] SYN 전송 (Sequence Number: X)
        // new Socket 호출 시 OS가 서버에 연결 요청을 보내고,
        // 서버로부터 SYN-ACK(2번)가 올 때까지 이 라인에서 대기
        try (Socket socket = new Socket("localhost", 8080)) {

            // 3. [OS 레벨] ACK 전송 (Acknowledgment: Y+1) - 자동 발생
            // 서버의 SYN-ACK를 받자마자 클라이언트 OS가 자동으로 ACK를 보냄
            // 이 3번 과정이 성공해야만 아래 라인으로 코드가 진행

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("서버로부터 받은 메시지: " + br.readLine());

        } catch (IOException e) {
            System.out.println("서버 연결 실패");
        }
    }
}