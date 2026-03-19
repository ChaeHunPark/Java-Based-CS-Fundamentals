
class SettingManager {
    // 1. 딱 하나만 존재할 인스턴스(미리 생성) Eager Init
    private static final SettingManager instance = new SettingManager();

    // Encapsulation
    private int count = 0; // 같은 객체를 사용하는가 확인하는 용도

    // Race Condition을 생각하면? synchronized가 필요함
    public synchronized void addCount() {
        count++;
    }

    public int getCount() {
        return count;
    }


    // 2. 외부에서 new 못하게 막기
    private SettingManager() {
        System.out.println(" SettingManager 인스턴스 생성 완료");
    }

    public static SettingManager getInstance() {
        return instance;
    }

}

public class Step01_Singleton {
    public static void main(String[] args) {
        // SettingManager s = new SettingManager(); // 에러 발생 (private)

        // 호출 세번 해보기
        SettingManager s1 = SettingManager.getInstance();
        SettingManager s2 = SettingManager.getInstance();
        SettingManager s3 = SettingManager.getInstance();

        s1.addCount();
        System.out.println("s1, s2, s3 : " + s1.getCount() + ", " + s2.getCount() + ", " + s3.getCount());
        s2.addCount();
        System.out.println("s1, s2, s3 : " + s1.getCount() + ", " + s2.getCount() + ", " + s3.getCount());
        s3.addCount();
        System.out.println("s1, s2, s3 : " + s1.getCount() + ", " + s2.getCount() + ", " + s3.getCount());



    }
}
