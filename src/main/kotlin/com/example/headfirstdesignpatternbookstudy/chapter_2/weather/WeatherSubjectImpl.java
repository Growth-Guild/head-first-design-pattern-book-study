package com.example.headfirstdesignpatternbookstudy.chapter_2.weather;

import java.util.List;

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
