package com.dimitrov_solutions.weight_tracker.controllers;

import com.dimitrov_solutions.weight_tracker.security.JwtService;
import org.springframework.web.bind.annotation.RequestHeader;
import underDev.Chart;
import com.dimitrov_solutions.weight_tracker.services.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/charts")
public class ChartController {
    private final ChartService chartService;
    private final JwtService jwtService;

    @Autowired
    public ChartController(ChartService chartService, JwtService jwtService) {
        this.chartService = chartService;
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Chart>> fetchAllUserCharts(@RequestHeader(name = "Authorization") String token) {
        String email = jwtService.extractUserName(jwtService.cleanToken(token));
        return ResponseEntity.of(chartService.findAllByCredentials(email));
    }

    @GetMapping("/weight-loss/{cName}")
    public ResponseEntity<Chart> fetchChartByName(@PathVariable("cName") String cName) {
        return ResponseEntity.of(chartService.findByName(cName));
    }

}
