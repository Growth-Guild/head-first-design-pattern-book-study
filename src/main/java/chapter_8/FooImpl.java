package chapter_8;

public class FooImpl extends AbstractFoo {
    @Override
    protected void introduce() {
        System.out.println("My name is FooImpl");
    }
}
