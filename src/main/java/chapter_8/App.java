package chapter_8;

public class App {
    public static void main(String[] args) {
        AbstractFoo foo = new FooImpl();
        foo.doSomething();

        FunctionalFoo functionalFoo = new FunctionalFoo();
        functionalFoo.doSomething(() -> System.out.println("Hello!! I'm FunctionalFoo"));
    }
}
