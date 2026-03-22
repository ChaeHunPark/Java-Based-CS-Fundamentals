
// 1. 옵저버 인터페이스 (알림을 받을 규격)

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(int level);
}

// 2. 알림을 보내는 주체 (캐릭터)
class GameCharacter {
    private List<Observer> observers = new ArrayList<>();
    private int level = 1;

    // 구독자 등록
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 레벨업
    public void levelUp() {
        level++;
        System.out.println("레벨이 " + level + "이 되었습니다.");
        notifyObservers();
    }

    // 모든 구독자에게 알림 전송
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(level);
        }
    }
}

// 3. 옵저버 구현체들
class AchievementSystem implements Observer {

    @Override
    public void update(int level) {
        if(level == 10) {
            System.out.println("[초보 탈출] 레벨 10 도달!");
        }
    }
}

class UILogSystem implements Observer {
    @Override
    public void update(int level) {
        System.out.println("[UI 로그] 화면에 'Level Up!' 이펙트를 표시합니다.");
    }
}

class GuildSystem implements Observer {

    @Override
    public void update(int level) {
        System.out.println("길드원들에게 레벨업 소식을 전달합니다.");
    }
}


public class Step04_Observer {
    public static void main(String[] args) {
        GameCharacter hero = new GameCharacter();

        // 시스템 등록 (구독 시작)
        hero.addObserver(new AchievementSystem());
        hero.addObserver(new UILogSystem());
        hero.addObserver(new GuildSystem());

        // 게임 진행 중 레벨업 발생
        hero.levelUp();

        // 반복 레벨업 (Lv.10 업적 확인용)
        for(int i=0; i<8; i++) hero.levelUp();
    }
}
