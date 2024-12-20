package com.dimitrov_solutions.weight_tracker.unit.domain;

import com.dimitrov_solutions.weight_tracker.models.chart.Chart;
import underDev.ChartBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChartBuilderTest {

    ChartBuilder builder;

    @BeforeEach
    void init() {
        builder = new ChartBuilder();
    }

    @Test
    void Build_returns_a_chart() {
        Chart chart = builder.build();

        var expected = Chart.class;
        assertEquals(expected, chart.getClass(), "Returned builder class should match " + Chart.class);
    }

    @Test
    void Build_named_chart() {
        String name = "myName";
        Chart chart = builder.name(name).build();

        assertEquals(chart.getName(), name, "both names should be " + name);
    }

}
