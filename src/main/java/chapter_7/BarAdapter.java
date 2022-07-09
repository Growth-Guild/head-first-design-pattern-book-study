package chapter_7;

public class BarAdapter implements Foo {
    private Bar bar;

    public BarAdapter(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void greeting() {
        bar.hello();
    }
}
