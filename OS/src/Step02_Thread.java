public class Step02_Thread {
    public static void main(String[] args) {
        // 현재 프로세스 ID 확인 (PID)
        long pid = ProcessHandle.current().pid();
        System.out.println("현재 PID: " +  pid);

        // 2. 첫 번쨰 스레드 양파 썰기
        Thread hand1 = new Thread(() -> {
           for (int i=1; i<=5; i++) {
               System.out.println(" 양파 써는중 ... (" + i + "/5)");
               try {Thread.sleep(500);}catch (InterruptedException e){}
           }
        });

        // 2. 두 번쨰 스레드 고추 썰기
        Thread hand2 = new Thread(() -> {
            for (int i=1; i<=5; i++) {
                System.out.println(" 고추 써는중 ... (" + i + "/5)");
                try {Thread.sleep(500);}catch (InterruptedException e){}
            }
        });

        // 요리 시작
        System.out.println("요리사가 양손을 쓰기 시작합니다.");
        hand1.start();
        hand2.start();

    }
}
