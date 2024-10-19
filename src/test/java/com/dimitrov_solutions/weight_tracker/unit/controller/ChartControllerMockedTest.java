package com.dimitrov_solutions.weight_tracker.unit.controller;

import com.dimitrov_solutions.weight_tracker.controllers.ChartController;
import underDev.Chart;
import com.dimitrov_solutions.weight_tracker.services.ChartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChartControllerMockedTest {

    @MockBean
    ChartService chartService;
    @Autowired
    ChartController chartController;
    String email;
    String chartName;

    @BeforeEach
    void init() {
        email = "somemail@gmail.com";
        chartName = "randomName";
    }

    @Test
    void fetch_all_user_charts_with_full_list() {
        List<Chart> expectedList = List.of(new Chart("chart1"), new Chart("chart2"));
        Optional<List<Chart>> expected = (Optional.of(List.of(new Chart("chart1"), new Chart("chart2"))));
        when(chartService.findAllByCredentials(email)).thenReturn(expected);

        ResponseEntity<List<Chart>> actual = chartController.fetchAllUserCharts();

        assertEquals(ResponseEntity.of(expected), actual, "Lists should be the same");

        verify(chartService, times(1)).findAllByCredentials(email);
    }

    @Test
    void return_response_code_404_from_empty_list_optional() {
        ResponseEntity<Chart> expected = ResponseEntity.of(Optional.empty());
        when(chartService.findAllByCredentials(email)).thenReturn(Optional.empty());

        ResponseEntity<List<Chart>> actual = chartController.fetchAllUserCharts();
        assertEquals(expected.getStatusCode(), actual.getStatusCode(), "both responseEntities status code should be " + expected.getBody());
        assertEquals(expected.getBody(), actual.getBody(), "both responseEntities body should be " + HttpStatus.NOT_FOUND);

        verify(chartService, times(1)).findAllByCredentials(email);
    }

    @Test
    void Fetch_user_chart_based_on_Cname() {
        ResponseEntity<Chart> expected = ResponseEntity.of(Optional.of(new Chart(chartName)));
        when(chartService.findByName(chartName)).thenReturn(Optional.of(new Chart(chartName)));

        ResponseEntity<Chart> actual = chartController.fetchChartByName(chartName);

        assertNotNull(actual);
        assertEquals(expected.getStatusCode(), actual.getStatusCode(), "status code should be equal to = " + expected.getStatusCode());
        assertEquals(expected.getBody(), actual.getBody(), "chart objects should be equal = ");

        verify(chartService, times(1)).findByName(chartName);
    }

    @Test
    void Fetch_user_chart_returns_404() {
        ResponseEntity<Chart> expected = ResponseEntity.of(Optional.empty());
        when(chartService.findByName(chartName)).thenReturn(Optional.empty());

        ResponseEntity<Chart> actual = chartController.fetchChartByName(chartName);

        assertEquals(expected.getStatusCode(), actual.getStatusCode(), "both status codes should equal" + HttpStatus.NOT_FOUND);
        assertNull(expected.getBody());
    }

}
