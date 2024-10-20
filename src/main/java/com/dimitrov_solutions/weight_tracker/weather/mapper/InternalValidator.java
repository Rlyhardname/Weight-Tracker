package com.dimitrov_solutions.weight_tracker.weather.mapper;

import com.fasterxml.jackson.databind.JsonNode;

public interface InternalValidator {
    String validateToString(JsonNode node,String fieldName);

    Double validateToDouble(JsonNode node, String fieldName);

    Integer validateToInt(JsonNode node, String fieldName);

}
