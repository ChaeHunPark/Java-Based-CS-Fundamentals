import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Step03_QueryParamServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("파라미터 분석 서버 가동 http://localhost8080/?name=Yourname 접속하기");
            while (true){
                try(Socket socket = serverSocket.accept()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    // 1. 요청 라인 읽기 (GET /?name=name&id=7 HTTP1.1)
                    String requestLine = br.readLine();
                    if(requestLine == null) continue;
                    System.out.println("\n[Request Line] " + requestLine);

                    // 2. URL에서 파라미터 부분만 추출하기
                    String[] tokens = requestLine.split(" ");
                    String path = tokens[1]; // "/?name=name&id=7"

                    String name = "Guest";
                    String id = "Unknown";

                    if(path.contains("?")) {
                        String queryString = path.split("\\?")[1]; // "name=name&id=7"
                        String[] params = queryString.split("&"); // ["name=name", "id=7"]

                        for (String param : params) {
                            String[] keyValue = param.split("="); //["name", "name"]
                            if (keyValue[0].equals("name")) name = keyValue[1];
                            if (keyValue[0].equals("id")) id = keyValue[1];
                        }
                    }

                    // 3. 동적 응답 생성
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("HTTP/1.1 200 OK");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    out.println("<h1>Welcome to Java Server!</h1>");
                    out.println("<h3>접속자 성함: " + name + "</h3>");
                    out.println("<h3>고유 ID: " + id + "</h3>");
                    out.println("<p>URL 파라미터를 서버가 성공적으로 파싱했습니다.</p>");

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
