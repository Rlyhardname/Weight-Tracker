package com.dimitrov_solutions.weight_tracker.unit.controller;

import com.dimitrov_solutions.weight_tracker.controllers.ChartController;
import com.dimitrov_solutions.weight_tracker.security.JwtService;
import com.dimitrov_solutions.weight_tracker.services.ChartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.dimitrov_solutions.weight_tracker.models.chart.Chart;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChartController.class)
public class ChartControllerMockedTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    ChartService chartService;
    @MockBean
    JwtService jwtService;
    String email;
    String chartName;
    private final String jwtBearer = "bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc29tZW1haWxAbXltYWlsLmNvbSIsImlhdCI6MTcyOTk1MDQ1NSwiZXhwIjoxNzMyOTUwNDU1fQ.n1XavNKcS8KnmzrXa6V5CVsxg0DwP1g2-6h1_GT79fk";

    @BeforeEach
    void init() {
        email = "somemail@gmail.com";
        chartName = "randomName";
    }

    @Test
    @WithMockUser(username = "asomemail@mymail.com", password = "qwertyuiozzz")
    void fetch_all_user_charts_with_full_list() throws Exception {
        List<Chart> expectedList = List.of(new Chart("chart1"), new Chart("chart2"));
        Optional<List<Chart>> expected = (Optional.of(List.of(new Chart("chart1"), new Chart("chart2"))));
        when(chartService.findAllByCredentials(email)).thenReturn(expected);


        mockMvc.perform(get("/charts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtService))
                .andExpect(status().isOk());
    }
}