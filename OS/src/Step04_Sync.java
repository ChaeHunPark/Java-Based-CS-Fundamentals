
class Kitchen {
    int saltCount = 0; // 공유 자원 (소금량)

    // 소금을 1씩 1000번 넣는 작업
    public void addSalt() {
        saltCount++;
    }

    public synchronized void addSaltSynchro() {
        saltCount++;
    }

}

public class Step04_Sync {
    public static void main(String[] args) throws InterruptedException {
        Kitchen kitchen1 = new Kitchen();
        Kitchen kitchen2 = new Kitchen();

        // 요리사 두명이 각각 소금을 10,000번씩 넣는다.
        Thread cookA = new Thread( () -> {
            for (int i = 0; i < 10000; i++) {
                kitchen1.addSalt();
            }
        });

        Thread cookB = new Thread( () -> {
            for (int i = 0; i < 10000; i++) {
                kitchen1.addSalt();
            }
        });

        // 요리사 두명이 각각 소금을 10,000번씩 넣는다.
        Thread cookC = new Thread( () -> {
            for (int i = 0; i < 10000; i++) {
                kitchen2.addSaltSynchro();
            }
        });

        Thread cookD = new Thread( () -> {
            for (int i = 0; i < 10000; i++) {
                kitchen2.addSaltSynchro();
            }
        });

        cookA.start();
        cookB.start();
        cookC.start();
        cookD.start();

        cookA.join(); // 작업이 끝날 때까지 대기
        cookB.join();
        cookC.join(); // 작업이 끝날 때까지 대기
        cookD.join();

        // 기대 값은 20,000
        System.out.println("최종 소금량: " + kitchen1.saltCount);
        System.out.println("최종 소금량(synchronized): " + kitchen2.saltCount);
    }
}
