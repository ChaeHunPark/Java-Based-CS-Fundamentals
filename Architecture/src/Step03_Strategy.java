// 1. 전략 인터페이스 (무기 선택)
interface WeaponStrategy {
    void attack();
}



// 2. 구체 적인 전략들
class Gun implements WeaponStrategy {

    @Override
    public void attack() {
        System.out.println("총으로 무기로 변경 및 공격");
    }
}

class Sword implements WeaponStrategy {

    @Override
    public void attack() {
        System.out.println("검으로 무기로 변경 및 공격");
    }
}

// 1.1 전략 인터페이스2 (이동)
interface MoveStrategy {
    void move();
    boolean canAttack();
}

class Walking implements MoveStrategy {

    @Override
    public void move() {
        System.out.println("뚜벅뚜벅 걷습니다.");
    }

    @Override
    public boolean canAttack() {
        return true;
    }
}

class Horse implements MoveStrategy {

    @Override
    public void move() {
        System.out.println("말을 타고 빠르게 달립니다.");
    }

    @Override
    public boolean canAttack() {
        return true;
    }
}

class Airplane implements MoveStrategy {

    @Override
    public void move() {
        System.out.println("비행기를 타고 날아 갑니다.");
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}

// 3. 전략을 사용하는 주체 Character
class Character {
    private WeaponStrategy weaponStrategy;
    private MoveStrategy moveStrategy;

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setWeaponStrategy(WeaponStrategy weaponStrategy){
        this.weaponStrategy = weaponStrategy;
    }

    public void executeWeapon() {
        if(weaponStrategy == null) {
            System.out.println("무기가 없습니다.");
            return;
        }


//        if(moveStrategy instanceof Airplane) { // Tell, Don'task, 객체에게 묻지말고 시켜라
//            System.out.println("비행기를 타고 공격할 수 없습니다. 이동수단을 바꿔주세요.");
//        }
        // canAttack 메서드로 대체, 만약 이동수단이 100개라면? 100번 물어봐야한다.

        if(!moveStrategy.canAttack()) {
            System.out.println("현재 상태에서는 공격할 수 없습니다 이동 수단을 바꿔주세요.");
            return;
        }

        weaponStrategy.attack();
    }

    public void executeMove() {
        if(moveStrategy == null) {
            System.out.println("탈 것을 선택하세요.");
        }
        moveStrategy.move();
    }
}




public class Step03_Strategy {
    public static void main(String[] args) {
        Character character = new Character();

        character.setMoveStrategy(new Walking());
        character.setWeaponStrategy(new Gun());

        character.executeMove();
        character.executeWeapon();

        character.setMoveStrategy(new Airplane());

        character.executeWeapon();
    }
}
