package com.example.headfirstdesignpatternbookstudy.chapter_2.weather;

import java.util.Objects;

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
