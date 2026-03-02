public class Step03_Scheduling {
    public static void main(String[] args) {
        // 1. VIP 요리 (우선순위 높음)
        Thread vipTask = new Thread(() -> {
           for (int i = 0; i < 5; i++){
               System.out.println("VIP 요리 만드는 중...");
               Thread.yield(); // 다른 스레드에게 양보 시도 (스케줄링 관여)
           }
        });

        // 2. 일반 요리 (우선순위 낮음)
        Thread normalTask = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("일반 요리 만드는 중...");
            }
        });

        vipTask.setPriority(Thread.MAX_PRIORITY);
        normalTask.setPriority(Thread.MIN_PRIORITY);

        System.out.println("요리 순서를 결정합니다.");
        vipTask.start();
        normalTask.start();

    }
}
