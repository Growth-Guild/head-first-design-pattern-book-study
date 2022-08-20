package chapter_11;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Person ownerProxy = getOwnerProxy(new PersonImpl("Hello", "Male"));
        System.out.println("이름: " + ownerProxy.getName());

        try {
            ownerProxy.setGeekRating(10);
        } catch (Exception e) {
            System.out.println("본인 프록시에는 호출할 수 없습니다.");
        }
    }

    public static Person getOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person)
        );
    }
}
