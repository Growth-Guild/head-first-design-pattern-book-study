package chapter_9.composite;

import java.util.List;

public class SimpleMain {
    public static void main(String[] args) {
        FooComponent foo = new Foo(
                List.of(
                        new SubFoo("Sub Hello"),
                        new SubFoo("Sub World")
                ),
                "Foo Hello World!"
        );

        System.out.println(foo.getName());
    }
}
