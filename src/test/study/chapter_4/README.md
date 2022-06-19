# Chapter 4. 팩토리 패턴 (Factory Pattern)

### new 키워드의 문제점
* new 키워드는 구체 클래스를 이용하여 인스턴스를 생성하도록 한다.
* 이는 인터페이스가 아닌 특정 구현에 의존성이 생기도록 한다.
* 특정 구현에 의존성이 생기면 테스트가 어렵고, 변경에 대한 영향도가 커진다.
```java
public class PizzaStore {
    
    public Pizza orderPizza(String type) {
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
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
```
* 만약 새로운 피자 메뉴가 추가된다면 PizzaStore 코드에 수정이 필요해진다.

### 팩토리
* 팩토리는 객체 생성을 담당한다. 이를 통해 객체 생성에 대한 기능을 캡슐화 시킨다.
```java
public class SimplePizzaFactory {
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
```
```java
public class PizzaStore {
    private SimplePizzaFactory simplePizzaFactory;

    public PizzaStore(SimplePizzaFactory simplePizzaFactory) {
        this.simplePizzaFactory = simplePizzaFactory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = simplePizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
```
* 위와 같이 팩토리를 통해 객체 생성을 캡슐화 시키면 새로운 피자 메뉴가 추가되더라도 PizzaStore는 변경으로부터 안전하다.

### 정적 팩토리
* 정적 팩토리는 객체 생성을 위해 인스턴스를 생성하지 않아도 된다.
* 하지만 서브클래스를 만들어서 객체 생성 메소드의 행동을 변경할 수 없다는 단점이 있다.

## 팩토리의 추상화
* 위의 예제에서는 팩토리 클래스가 구체 타입이고, PizzaStore가 직접 의존하고 있다.
* 이를 추상화하여 인터페이스에 의존하도록 아래와 같이 수정하였다.
```java
public abstract class PizzaFactory {
    public abstract Pizza createPizza(String type);
}

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
```
```java
public class PizzaStore {
    private PizzaFactory pizzaFactory;

    public PizzaStore(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = pizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
```

### 팩토리 메서드 패턴
* 팩토리 메서드 패턴은 객체의 생성을 서브 클래스로 캡슐화시킨다.

```java
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    
    abstract Pizza createPizza(String type);
}
```
```java
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
            pizza = new DefaultPizza();
        }
        return pizza;
    }
}
```

## 의존성 역전 원칙 (Dependency Inversion Principle)

### 디자인 원칙
> 추상화된 것에 의존하게 만들고 구상 클래스에 의존하지 않게 만든다.

* 고수준 모듈이 저수준 모듈에 의존해서는 안되며, 항상 추상화에 의존하게 만들어야 한다.
* 고수준 모듈에서 저수준 모듈에 의존하게 되면 테스트하기가 어렵고, 저수준 모듈의 변경 사항이 고수준 모듈에도 영향을 미치게 된다.

### 추상 팩토리 패턴 (Abstract Factory Pattern)
* 구상 클래스에 의존하지 않고 서로 연관되거나 의존적인 객체로 이루어진 객체를 생성하는 인터페이스를 제공한다.
* 구상 클래스는 서브클래스에서 만든다.

```java
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Clams createClams();
}

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
```
```java
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
```
```java
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
```
* 추상 팩토리 패턴은 구체적인 클래스에 의존하지 않고 서로 연관되거나 의존적인 객체들의 조합을 만드는 인터페이스를 제공하는 패턴이다.
* 관련성 있는 여러 종류의 객체를 일관된 방식으로 생성하는 경우에 유용하다.
* 어떤 구상 클래스가 필요할지 미리 알 수 없을 때에도 유용한다.
