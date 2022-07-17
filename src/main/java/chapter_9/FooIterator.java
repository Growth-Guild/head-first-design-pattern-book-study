package chapter_9;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class FooIterator implements Iterable<Foo> {
    private List<Foo> foos;
    private int position = 0;

    public FooIterator(List<Foo> foos) {
        this.foos = foos;
    }

    @NotNull
    @Override
    public Iterator<Foo> iterator() {
        return foos.iterator();
    }
}
