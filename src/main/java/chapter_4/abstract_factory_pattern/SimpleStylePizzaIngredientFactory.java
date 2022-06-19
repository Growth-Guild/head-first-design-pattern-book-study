package chapter_4.abstract_factory_pattern;

public class SimpleStylePizzaIngredientFactory implements PizzaIngredientFactory {

    @Override
    public Dough createDough() {
        return new Dough() {};
    }

    @Override
    public Sauce createSauce() { return new Sauce() {}; }

    @Override
    public Cheese createCheese() { return new Cheese() {}; }

    @Override
    public Veggies[] createVeggies() { return new Veggies[0]; }

    @Override
    public Pepperoni createPepperoni() { return new Pepperoni() {};}

    @Override
    public Clams createClams() { return new Clams() {}; }
}
