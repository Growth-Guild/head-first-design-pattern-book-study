package com.example.headfirstdesignpatternbookstudy.chapter_1.duck;

import chapter_1.duck.Duck;
import chapter_1.duck.FlyWithWings;
import chapter_1.duck.MallardDuck;
import org.junit.jupiter.api.Test;

class MallardDuckTest {

    @Test
    void flyTest() {
        Duck duck = new MallardDuck(new FlyWithWings());
        duck.performQuack();
    }
}
