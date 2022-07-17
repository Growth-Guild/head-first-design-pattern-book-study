package chapter_9.iterator;

import java.util.List;

public class IteratorSimpleMain {
    public static void main(String[] args) {
        List<Foo> foos = List.of(
                new Foo("hello"),
                new Foo("World"),
                new Foo ("goodbye")
        );

        FooIterator fooIterator = new FooIterator(foos);

        for (Foo foo : fooIterator) {
            System.out.println(foo.getName());
        }
    }
}
