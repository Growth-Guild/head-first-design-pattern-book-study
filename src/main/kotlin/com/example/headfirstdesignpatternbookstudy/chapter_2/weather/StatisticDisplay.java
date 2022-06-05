package com.example.headfirstdesignpatternbookstudy.chapter_2.weather;

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
