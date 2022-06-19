package chapter_4.abstract_factory_pattern;

public class SimpleStylePizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("greek")) {
            pizza = new GreekPizza();
        } else if (type.equals("PepperoniPizza")) {
            pizza = new PepperoniPizza();
        } else {
            pizza = new DefaultPizza(new SimpleStylePizzaIngredientFactory());
        }
        return pizza;
    }
}
