package chapter_8;

public abstract class AbstractFoo {

    public void doSomething() {
        greeting();
        introduce();
        bye();
    }

    private void greeting() {
        System.out.println("Greeting!");
    }

    protected abstract void introduce();

    private void bye() {
        System.out.println("Bye!");
    }
}
