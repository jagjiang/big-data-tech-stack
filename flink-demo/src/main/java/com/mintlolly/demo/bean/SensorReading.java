package com.mintlolly.demo.bean;

/**
 * Created on 2021/7/14
 *
 * @author jiangbo
 * Description:
 */
public class SensorReading {
    private String id;
    private Long time;
    private double temperature;

    public SensorReading(String id, Long time, double temperature) {
        this.id = id;
        this.time = time;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", temperature=" + temperature +
                '}';
    }
}
