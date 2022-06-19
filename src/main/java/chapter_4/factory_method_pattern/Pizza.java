package chapter_4.factory_method_pattern;

public abstract class Pizza {
    public abstract void prepare();

    public abstract void bake();

    public abstract void cut();

    public abstract void box();
}
