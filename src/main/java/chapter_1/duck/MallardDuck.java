package chapter_1.duck;

public class MallardDuck extends Duck {
    public MallardDuck(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    @Override
    protected void display() {
        System.out.println("저는 물오리 입니다.");
    }
}
