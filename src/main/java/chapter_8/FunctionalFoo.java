package chapter_8;

public class FunctionalFoo {

    public void doSomething(Runnable runnable) {
        greeting();
        runnable.run();
        bye();
    }

    private void greeting() {
        System.out.println("Greeting!");
    }

    private void bye() {
        System.out.println("Bye!");
    }
}
