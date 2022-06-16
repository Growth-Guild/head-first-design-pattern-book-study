package com.example.headfirstdesignpatternbookstudy.chapter_2.weather;

import chapter_2.weather.WeatherData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    @Test
    @DisplayName("WeatherData 빌더 테스트")
    void createTest() {
        // given
        float temperature = 24.7f;
        float humidity = 54.3f;
        float pressure = 1.0f;

        // when
        WeatherData weatherData = new WeatherData.Builder()
                .temperature(temperature)
                .humidity(humidity)
                .pressure(pressure)
                .build();

        // then
        assertNotNull(weatherData);
        assertEquals(temperature, weatherData.getTemperature());
        assertEquals(humidity, weatherData.getHumidity());
        assertEquals(pressure, weatherData.getPressure());
    }
}
