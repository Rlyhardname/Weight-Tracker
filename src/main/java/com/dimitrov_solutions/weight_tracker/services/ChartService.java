package com.dimitrov_solutions.weight_tracker.services;

import com.dimitrov_solutions.weight_tracker.models.chart.Chart;
import com.dimitrov_solutions.weight_tracker.repositories.ChartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartService {

    private final UserService userService;
    private final ChartRepository chartRepository;

    public ChartService(ChartRepository chartRepository, UserService userService) {
        this.chartRepository = chartRepository;
        this.userService = userService;
    }

    public Optional<Chart> findByName(String name) {
        return chartRepository.findByName(name);
    }

    public Optional<List<Chart>> findAllByCredentials(String email) {
        if (!userService.existsByEmail(email))
            return Optional.empty();

        return Optional.of(chartRepository.findByEmail(email));
    }


}
