package com.dimitrov_solutions.weight_tracker.repositories;

import underDev.Chart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChartRepository extends MongoRepository<Chart, String> {

    Optional<Chart> findByName(String name);

    List<Chart> findByEmail(String s);
}
