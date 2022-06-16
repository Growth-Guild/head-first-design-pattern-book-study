package com.example.headfirstdesignpatternbookstudy.chapter_2.weather;

import chapter_2.weather.Observer;
import chapter_2.weather.WeatherData;
import chapter_2.weather.WeatherSubject;
import chapter_2.weather.WeatherSubjectImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherSubjectImplTest {

    private WeatherSubject weatherSubject;
    private final float temperature = 24.6f;
    private final float humidity = 54.2f;
    private final float pressure = 1.1f;

    private final List<Observer> observers = new ArrayList<>();

    @BeforeEach
    void initWeatherSubject() {
        WeatherData weatherData = new WeatherData.Builder()
                .temperature(temperature)
                .humidity(humidity)
                .pressure(pressure)
                .build();
        this.weatherSubject = new WeatherSubjectImpl(observers, weatherData);
    }

    @Test
    void getCurrentDataTest() {
        // given
        WeatherData weatherData = new WeatherData.Builder()
                .temperature(temperature)
                .humidity(humidity)
                .pressure(pressure)
                .build();

        // when
        WeatherData currentData = weatherSubject.getCurrentData();

        // then
        assertNotSame(weatherData, currentData);
        assertEquals(weatherData, currentData);
    }

    @Test
    void registerObserver() {
        // given
        Observer observer = mock(Observer.class);

        // when
        weatherSubject.registerObserver(observer);

        // then
        assertFalse(observers.isEmpty());
    }

    @Test
    void removeObserver() {
        // given
        Observer observer = mock(Observer.class);
        weatherSubject.registerObserver(observer);

        // when
        weatherSubject.removeObserver(observer);

        // then
        assertTrue(observers.isEmpty());
    }

    @Test
    void measureWeatherData() {
        // given
        Observer observer = mock(Observer.class);
        weatherSubject.registerObserver(observer);
        WeatherData weatherData = new WeatherData.Builder()
                .temperature(21.4f)
                .humidity(58.8f)
                .pressure(1.0f)
                .build();

        // when
        weatherSubject.measureWeatherData(weatherData);

        // then
        verify(observer, times(1)).update(weatherData);
        assertEquals(weatherSubject.getCurrentData(), weatherData);
    }
}
