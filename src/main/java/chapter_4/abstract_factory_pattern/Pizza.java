package chapter_4.abstract_factory_pattern;

public abstract class Pizza {
    protected Dough dough;
    protected Sauce sauce;
    protected Veggies[] veggies;
    protected Cheese cheese;
    protected Pepperoni pepperoni;
    protected Clams clams;

    public abstract void prepare();

    public abstract void bake();

    public abstract void cut();

    public abstract void box();
}
