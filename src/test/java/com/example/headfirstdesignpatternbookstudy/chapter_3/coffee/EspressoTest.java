package com.example.headfirstdesignpatternbookstudy.chapter_3.coffee;

import chapter_3.coffee.Espresso;
import chapter_3.coffee.Mocha;
import org.junit.jupiter.api.Test;

class EspressoTest {

    @Test
    void espressoTest() {
        Mocha mochaEspresso = new Mocha(new Espresso());
        System.out.println(mochaEspresso.getDescription());
        System.out.println(mochaEspresso.getCost());
    }
}