package chapter_2.weather;

public interface WeatherSubject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    WeatherData getCurrentData();
    void measureWeatherData(WeatherData weatherData);
    void notifyObservers();
}
