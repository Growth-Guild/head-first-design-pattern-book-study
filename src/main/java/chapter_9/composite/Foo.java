package chapter_9.composite;

import java.util.List;
import java.util.stream.Collectors;

public class Foo extends FooComponent {
    private List<FooComponent> fooComponents;
    private String name;

    public Foo(List<FooComponent> fooComponents, String name) {
        this.fooComponents = fooComponents;
        this.name = name;
    }

    @Override
    public String getName() {
        return name + "\n" +
                fooComponents.stream()
                        .map(FooComponent::getName)
                        .collect(Collectors.joining(", "));
    }
}
