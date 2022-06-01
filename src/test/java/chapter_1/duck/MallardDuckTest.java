package chapter_1.duck;

import org.junit.jupiter.api.Test;

class MallardDuckTest {

    @Test
    void flyTest() {
        Duck duck = new MallardDuck(new FlyWithWings());
        duck.performQuack();
    }
}
