package com.dimitrov_solutions.weight_tracker.unit.service;

import com.dimitrov_solutions.weight_tracker.models.Chart;
import com.dimitrov_solutions.weight_tracker.repositories.ChartRepository;
import com.dimitrov_solutions.weight_tracker.services.ChartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChartServiceMockedTest {
    String chartName;

    @MockBean
    private ChartRepository chartRepository;
    @Autowired
    private ChartService chartService;

    @BeforeEach
    void init() {
        chartName = "HelloMockedCharts";
    }

    @Test
    void Find_chart_by_Cname_if_exists() {
        Chart actual = new Chart(chartName);
        when(chartRepository.findByName(chartName)).thenReturn(Optional.of(actual));

        Optional<Chart> expected = chartService.findByName(chartName);

        assertTrue(expected.isPresent());
    }

    @Test
    void Creating_chart_if_Cname_is_available() {
        Chart actual = new Chart(chartName);
        when(chartRepository.save(actual)).thenReturn(actual);

        Chart expected = chartRepository.save(new Chart(chartName));

        assertEquals(expected, actual, "Both charts should have same property name = " + chartName);
    }

    @Test
    void Delete_chart_if_Cname_exists() {
        Chart searchedChart = new Chart(chartName);

        doNothing().when(chartRepository).delete(searchedChart);
        chartRepository.delete(searchedChart);
        when(chartRepository.findByName(searchedChart.getName())).thenReturn(Optional.empty());
        Optional<Chart> chartOpt = chartRepository.findByName(chartName);

        assertFalse(chartOpt.isPresent(), "Chart should not be found after deletion");

        verify(chartRepository, times(1)).delete(searchedChart);
    }

}
