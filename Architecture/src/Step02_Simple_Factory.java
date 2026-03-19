// 제품의 규격 (Interface)
interface Pizza{
    void make();
}


// 구체적인 제품들
class CheesePizza implements Pizza {

    @Override
    public void make() {
        System.out.println("치즈 피자 만드는중");
    }
}

class PepperoniPizza implements Pizza {

    @Override
    public void make() {
        System.out.println("페퍼로니 피자 만드는 중");
    }
}


// 3. 객체 생성을 담당하는 공장(Factory)

class PizzaFactory{
    public static Pizza createPizza(String type) {
        if (type.equalsIgnoreCase("cheese")) {
            return new CheesePizza();
        } else if(type.equalsIgnoreCase("pepperoni")) {
            return new PepperoniPizza();
        }
        return null;
    }
}


public class Step02_Simple_Factory {
    public static void main(String[] args) {
        // 클라이언트는 new CheesePizza()를 직접 하지 않는다.
        // 공장에 이름만 말하기
        Pizza pizza1 = PizzaFactory.createPizza("cheese");
        Pizza pizza2 = PizzaFactory.createPizza("pepperoni");

        if(pizza1 != null) pizza1.make();
        if(pizza2 != null) pizza2.make();
    }
}
