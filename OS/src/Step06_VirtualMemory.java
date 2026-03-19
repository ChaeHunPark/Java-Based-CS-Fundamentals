import java.util.ArrayList;
import java.util.List;

public class Step06_VirtualMemory {
    public static void main(String[] args) {
        System.out.println(">>> 가상 메모리 실험 시작: 메모리를 엄청나게 잡아먹어 봅시다.");
        List<byte[]> memoryGuzzler = new ArrayList<>();

        try {
            while (true) {
                // 10MB씩 계속 할당
                memoryGuzzler.add(new byte[10 * 1024 * 1024]);
                long freeMem = Runtime.getRuntime().freeMemory() / (1024 * 1024);
                System.out.println("현재 사용 가능한 힙 메모리: " + freeMem + " MB");

                // 너무 빨리 터지지 않게 살짝 대기
                Thread.sleep(100);
            }
        } catch (OutOfMemoryError e) {
            System.err.println("!!! 결국 메모리가 터졌습니다 (OutOfMemoryError) !!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
