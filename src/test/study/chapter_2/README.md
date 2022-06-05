# Chapter 2. 옵저버 패턴 (Observer Pattern)

## 책 예제의 문제 코드
```java
public class WeatherData {
    public void measurementsChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();
        
        currentConditionsDisplay.update(temp, humidity, pressure);
        statisticDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
    }
}
```
* 위 코드는 기상 상태의 변화를 측정하고 해당 결과들을 각 디스플레이에 통지하는 기능이다.

### 새로운 기기가 추가되었을 경우
* 새로운 기기가 추가되었는데, 해당 기기에서도 기상 상태 정보가 필요하게 되었다고 가정하면 위의 코드를 아래와 같이 수정해야한다.
```java
public class WeatherData {
    public void measurementsChanged() {
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();
        
        currentConditionsDisplay.update(temp, humidity, pressure);
        statisticDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
        newDivice.update(temp, humidity, pressure); // 새로운 기기가 추가되었다.
    }
}
```
* 만약 해당 기기가 필요없어졌을 경우엔 해당 코드를 다시 지우게 될 것이다.
* 이러한 변경의 문제점은 WeatherData 클래스와는 상관이 없는 변경이 이루어지고 있다는 점이다. (SRP 원칙 위배)
* 새로운 기기가 추가될 때마다 코드가 수정이 되어야 하기 때문에 OCP 원칙도 함께 위배되고 있다.

## Observer Pattern
* 특정 오브젝트의 이벤트를 발행하고, Subscriber들이 해당 이벤트를 통지 받을 수 있도록 확장성 있는 구조로 설계하는 패턴이다.
* Subscriber를 더 추가하거나 줄여도 해당 변경 사항이 기존의 Publisher와 Subscriber에게 영향을 주지 않는다.
* 한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체에게 통지되고 자동으로 내용이 갱신되는 방식으로 일대다 의존성을 정의한다.
![img.png](asset/observer_pattern.png)

* 옵저버 패턴을 활용하면 Subject와 Observer의 느슨한 결합을 만들 수 있다.
* 느슨한 결합으로 인해 Subject와 Observer의 독립적인 재사용이 가능하다.
* Subject와 Observer가 수정되어도 서로에게 영향을 미치지 않는다.

### 디자인 원칙
> 상호작용하는 객체 사이에는 가능하면 느슨한 결합을 사용해야 한다.

### Publisher-Subscriber 패턴과의 관계?
![img.png](asset/publisher_subscriber_pattern.png)
* 옵저버 패턴과 비슷하지만 Publisher-Subscriber 패턴은 Observer Pattern 보다 더 느슨한 결합을 구현한다.
* Publisher-Subscriber은 Subscriber가 서로 다른 유형의 메시지에 관심을 가질 수 있다.
* 미들웨어 시스템에서 종종 쓰인다.

### 개선해보기
#### WeatherData
```java
public class WeatherData {
    private final float temperature;
    private final float humidity;
    private final float pressure;

    private WeatherData(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData that = (WeatherData) o;
        return Float.compare(that.temperature, temperature) == 0 && Float.compare(that.humidity, humidity) == 0 && Float.compare(that.pressure, pressure) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, humidity, pressure);
    }

    public static class Builder {
        private float temperature;
        private float humidity;
        private float pressure;

        public Builder() {
        }

        public Builder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder humidity(float humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder pressure(float pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherData build() {
            return new WeatherData(temperature, humidity, pressure);
        }
    }
}
```
* 날씨 데이터를 주고 받을 Value Class이다.
* 책의 예제에서는 temperature, humidity, pressure 데이터를 각각 선언하였지만, 서로 연관되어 항상 함께 따라다니는 데이터들은 위와 같이 클래스에 모아서 캡슐화 해주는 것이 더 좋은 방법이므로 따로 클래스를 정의하였다.
* 해당 클래스는 Value Object이므로 불변으로 정의하였다.

#### Observer
```java
public interface Observer {
    void update(WeatherData weatherData);
}
```
* Subject로부터 갱신되는 데이터들을 통지받기 위한 인터페이스다.

#### WeatherSubject
```java
public interface WeatherSubject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    WeatherData getCurrentData();
    void measureWeatherData(WeatherData weatherData);
    void notifyObservers();
}

```
* 날씨 데이터를 관리하는 인터페이스이다.

#### WeatherSubjectImpl
```java
public class WeatherSubjectImpl implements WeatherSubject {
    private List<Observer> observers;
    private WeatherData weatherData;

    public WeatherSubjectImpl(List<Observer> observers, WeatherData weatherData) {
        this.observers = observers;
        this.weatherData = weatherData;
    }

    @Override
    public WeatherData getCurrentData() {
        return new WeatherData.Builder()
                .temperature(weatherData.getTemperature())
                .humidity(weatherData.getHumidity())
                .pressure(weatherData.getPressure())
                .build();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void measureWeatherData(WeatherData weatherData) {
        this.weatherData = new WeatherData.Builder()
                .temperature(weatherData.getTemperature())
                .humidity(weatherData.getHumidity())
                .pressure(weatherData.getPressure())
                .build();

        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getCurrentData());
        }
    }
}
```
* 이름은 편의상 구현하는 인터페이스 뒤에 impl을 붙여서 정의하였다.
* 날씨 데이터에 변경 사항이 있을 때 현재 등록된 observer들에게 날씨 데이터를 통지한다.

### StatisticDisplay
```java
public class StatisticDisplay implements Observer {
    private WeatherData weatherData;

    public StatisticDisplay() {}

    @Override
    public void update(WeatherData weatherData) {
        this.weatherData = weatherData;
        display();
    }
    
    public void display() {
        System.out.println("-------------------------");
        System.out.println("temperature : " + weatherData.getTemperature());
        System.out.println("humidity : " + weatherData.getHumidity());
        System.out.println("pressure : " + weatherData.getPressure());
        System.out.println("-------------------------");
    }
}
```

### 무엇이 개선되었는가?
* Subject와 Observer의 느슨한 결합을 통해 Observer의 변경사항이 Subject에 영향을 미치지 않는다.
* Subject 또한 Observer의 변경사항에 영향을 받지 않는다.
* Subject는 데이터가 변경되면 그저 자신에게 등록된 Observer들에게 통지할 뿐이다.

### Pull 방식
* 기존의 방식은 Subject가 Observer들에게 일방적으로 Data를 전달하는 Push 방식이다.
* Pull 방식은 그와 반대로 Observer가 필요할 때마다 Subject에게 데이터를 요청하는 방식이다.

### Pull 방식은 왜 사용하는가?
* Push 모델은 Subject와 Observer 간에 존재하는 성능 차이로 인해 문제가 발생할 수 있기 때문에 Pull 방식을 사용할 수 있다.

### Push 모델에서 어떤 문제가 발생하는가?
* Publisher가 Consumer보다 성능이 빠른 경우
  * Publisher는 빠른 속도로 이벤트를 Comsumer에게 계속 발행하지만 상대적으로 느린 Consumer는 발행되는 이벤트의 양을 감당할 수 없다.
  * Consumer는 빠르게 발생하는 이벤트를 감당하지 못하고 서버가 죽는 문제가 발생한다.
* Consumer가 Publisher보다 성능이 빠른 경우
  * Consumer가 매우 빠르게 동작하기 때문에 유휴상태가 계속 발생할 수 있다.
  * 이러한 불필요한 낭비를 줄이기 위해 Consumer의 처리 능력을 동적으로 변경되어야 한다.

### Backpressure
* Backpressure는 데이터 스트림을 제어하여 위와 같은 문제를 해결하여 견고하고 탄력적인 시스템을 만드는 메커니즘이다.
* 자신이 필요한 만큼 데이터를 요청하여 처리하는 Pull 방식이다.
