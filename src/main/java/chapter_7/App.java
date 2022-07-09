package chapter_7;

public class App {
    public static void main(String[] args) {
        Foo foo = new BarAdapter(new BarImpl());
        foo.greeting();
    }
}
