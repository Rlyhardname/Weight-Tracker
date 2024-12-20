package com.dimitrov_solutions.weight_tracker.models.chart;

import org.springframework.data.annotation.Id;
import underDev.ChartBuilder;

import java.util.Objects;

public class Chart {
    @Id
    private String id;
    private String email;
    private String name;

    public Chart(String name) {
        this.name = name;
    }

    public Chart(ChartBuilder builder) {
        name = builder.getName();
    }

    public Chart() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chart chart = (Chart) o;
        return Objects.equals(getEmail(), chart.getEmail()) && Objects.equals(getName(), chart.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getName());
    }

    @Override
    public String toString() {
        return "Chart{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
