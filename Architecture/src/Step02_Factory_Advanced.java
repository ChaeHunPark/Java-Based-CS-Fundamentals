// 1. 제품 규겨
interface Phone {
    void call();
}


class SamsungPhone implements Phone {

    @Override
    public void call() {
        System.out.println("삼성 폰 전화 거는중...");
    }
}

class ApplePhone implements Phone {

    @Override
    public void call() {
        System.out.println("애플 폰 전화 거는중...");
    }
}

// 2. 공장 규격 (추상화}
interface PhoneFactory {
    Phone createPhone(); // 폰을 제작한다는 행위만 정의
}

// 3. 전용 공장들 (구체화)
class SamsungPhoneFactory implements PhoneFactory {

    @Override
    public Phone createPhone() {
        return new SamsungPhone();
    }
}

class ApplePhoneFactory implements PhoneFactory {

    @Override
    public Phone createPhone() {
        return new ApplePhone();
    }
}



public class Step02_Factory_Advanced {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new SamsungPhoneFactory();
        Phone phone = phoneFactory.createPhone();
        phone.call();
    }
}
