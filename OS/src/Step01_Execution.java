public class Step01_Execution {
    public static void main(String[] args) {
        // 1. 현재 실행 중인 내 프로그램(Process)의 정보 객체 가져오기
        Runtime runtime = Runtime.getRuntime();

        // 2. 메모리 상태 확인 (Byte 단위를 MB로 변환 1024 * 1024)
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.println("사용 중인 램: " + usedMemory + "MB");
        System.out.println("JVM이 할당 받은 메모리 크기 : " + totalMemory + "MB");

        try{
            // 작업 관리자 확인용
            Thread.sleep(30000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
