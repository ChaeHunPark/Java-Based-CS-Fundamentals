public class Step05_Deadlock {
    public static void main(String[] args) {
        String salt = "소금통";
        String pepper = "후추통";

        // 요리사 A: 소금통 먼저 잡고 후추통 찾기
        Thread cookA = new Thread(() -> {
            synchronized (salt) {
                System.out.println("요리사A: 소금통 확보 (후추 기다리는 중...)");
                try { Thread.sleep(100); } catch (Exception e) {} // 충돌을 위한 지연

                synchronized (pepper) {
                    System.out.println("요리사A: 후추통까지 확보 요리 시작!");
                }
            }
        });

        // 요리사 B: 후추 먼저 잡고 소금 기다림
        Thread cookB = new Thread(() -> {
            synchronized (pepper) {
                System.out.println("요리사B: 후추통 확보 (소금 기다리는 중...)");
                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (salt) {
                    System.out.println("요리사B: 소금통까지 확보 요리 시작!");
                }
            }
        });

        cookA.start();
        cookB.start();


        // 요리사 B: 후추통 먼저 잡고 소금통 찾기

    }
}
