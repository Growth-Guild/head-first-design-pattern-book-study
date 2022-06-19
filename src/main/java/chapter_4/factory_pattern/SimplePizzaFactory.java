package chapter_4.factory_pattern;

public class SimplePizzaFactory extends PizzaFactory {

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("greek")) {
            pizza = new GreekPizza();
        } else if (type.equals("PepperoniPizza")) {
            pizza = new PepperoniPizza();
        } else {
            pizza = new DefaultPizza();
        }
        return pizza;
    }
}
