# Chapter 5. 싱글톤 패턴 (Singleton Pattern)

## 싱글톤 패턴
* 클래스 인스턴스를 하나만 만들고, 그 인스턴스로의 전역 접근을 제공한다.
* 인스턴스가 단 하나만 생성되는 것을 보장해야 한다.
* 어디서든 싱글톤 인스턴스에 접근할 수 있도록 전역 접근 지점을 제공해야한다.

```java
public class Singleton {
    private static Singleton uniqueInstance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```

### 싱글톤 패턴 사용 시, 고려해야할 점
* 싱글톤 패턴은 Thread safe 하게 구현되지 않으면 멀티 쓰레드 환경에서 2개 이상의 싱글톤 인스턴스가 생성될 수 있다.
```java
public class Singleton {
    private static Singleton uniqueInstance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```
* 위와 같이 synchronized 키워드를 추가하여 한 스레드가 메서드 사용을 끝내기 전까지 다른 쓰레드는 대기하도록 한다.
* synchronized 키워드를 통해 여러 쓰레드가 동시에 접근하더라도 동시에 실행되지 않는다. 
* 하지만 getInstance()를 호출할 때마다 매번 동기화를 진행하므로 성능 문제가 발생할 수 있다.

### 멀티 쓰레딩 환경에서 싱글톤의 문제 해결 방법
1. getInstance()의 속도가 그다지 중요하지 않다면 냅둔다.
2. 인스턴스가 필요할 때는 생성하지 말고 처음부터 만든다. (Eager Initialization)
```java
public class Singleton {
    private static Singleton uniqueInstance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
```
3. 'DCL'을 사용하여 getInstance()에서 동기화되는 부분을 줄인다. (DCL: Double-Checked Locking)
```java
public class Singleton {
    private volatile static Singleton uniqueInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
```

### volatile?
* 각 스레드가 해당 변수의 값을 메인 메모리에서 직접 읽어온다.
* volatile 변수에 대한 각 write는 즉시 메인 메모리로 플러시 된다.
* 쓰레드가 변수를 캐시하기로 결정하면 각 read/write 시 메인 메모리와 동기화된다.

### Eager Initialization
* 애플리케이션 실행 시, 싱글톤 객체를 미리 생성해 놓기 때문에 객체 생성 비용이 크게 들어가지 않는 경우에 사용한다.

### Lazy Initialization
* Eager Initialization과 반대로 클래스의 인스턴스가 사용되는 시점에 싱글톤 인스턴스를 생성한다.
* 최초 사용 시점까지 초기화를 미루므로 메모리를 효율적으로 사용할 수 있다.
* 위에서 살펴보았듯이 멀티 쓰레드 환경에서 동시에 getInstance() 메서드가 실행되어 여러 인스턴스가 생성될 수 있으므로 주의해야 한다.
