package chapter_9.composite;

public class SubFoo extends FooComponent{
    private String name;

    public SubFoo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
