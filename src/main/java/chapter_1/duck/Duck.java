package chapter_1.duck;

public abstract class Duck {
    protected FlyBehavior flyBehavior;

    public void performQuack() {
        flyBehavior.fly();
    }

    protected abstract void display();
}
