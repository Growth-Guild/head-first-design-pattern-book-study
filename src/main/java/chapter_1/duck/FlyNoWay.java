package chapter_1.duck;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("No fly..");
    }
}
