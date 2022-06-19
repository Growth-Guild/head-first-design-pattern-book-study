package chapter_4.abstract_factory_pattern;

public class DefaultPizza extends Pizza {
    private PizzaIngredientFactory pizzaIngredientFactory;

    public DefaultPizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        this.dough = pizzaIngredientFactory.createDough();
        this.sauce = pizzaIngredientFactory.createSauce();
        this.veggies = pizzaIngredientFactory.createVeggies();
        this.cheese = pizzaIngredientFactory.createCheese();
        this.pepperoni = pizzaIngredientFactory.createPepperoni();
        this.clams = pizzaIngredientFactory.createClams();
    }

    @Override
    public void bake() {}

    @Override
    public void cut() {}

    @Override
    public void box() {}
}
