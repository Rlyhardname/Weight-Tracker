package com.dimitrov_solutions.weight_tracker.models.weather;

import com.dimitrov_solutions.weight_tracker.models.weather.dto.WeatherDetailsDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WeatherDetailsDtoMapper extends JsonDeserializer<WeatherDetailsDto> implements InternalValidator {
    public static final String ENDPOINT_NOT_PRODUCING_FIELD = "Endpoint at: %s%n doesn't contain field: %s";
    public static final Logger logger = LoggerFactory.getLogger(WeatherDetailsDtoMapper.class);
    private String url;

    public WeatherDetailsDto mapTo(String body, String url) {
        try {
            this.url = url;
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(WeatherDetailsDto.class, this);
            mapper.registerModule(module);
            return mapper.readValue(body, WeatherDetailsDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public WeatherDetailsDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        Double temp = validateToDouble(node.get("main").get("temp"), "main.temp");
        Double clouds = validateToDouble(node.get("clouds").get("all"), "clouds.all");

        return new WeatherDetailsDto(temp, clouds, "rain", null, null);
    }


    @Override
    public String validateToString(JsonNode node, String fieldName) {
        try {
            return node.asText();
        } catch (NullPointerException e) {
            logEndpointNotProducingField(fieldName, "info");
        }

        return "";
    }

    @Override
    public Double validateToDouble(JsonNode node, String fieldName) {
        try {
            return node.asDouble();
        } catch (NullPointerException e) {
            logEndpointNotProducingField(fieldName, "debug");
            if (fieldName.equals("temp")) {
                throw new BadDataApiResponseException();
            }
        }

        return 0.0;
    }

    @Override
    public Integer validateToInt(JsonNode node, String fieldName) {
        try {
            return node.asInt();
        } catch (NullPointerException e) {
            logEndpointNotProducingField(fieldName, "info");
        }

        return 0;
    }

    private void logEndpointNotProducingField(String fieldName, String severity) {
        switch (severity.toLowerCase()) {
            case "trace":
                logger.trace(String.format(ENDPOINT_NOT_PRODUCING_FIELD, url, fieldName));
                break;
            case "info":
                logger.info(String.format(ENDPOINT_NOT_PRODUCING_FIELD, url, fieldName));
                break;
            case "debug":
                logger.debug(String.format(ENDPOINT_NOT_PRODUCING_FIELD, url, fieldName));
                break;
            case "error":
                logger.error(String.format(ENDPOINT_NOT_PRODUCING_FIELD, url, fieldName));
                break;
            default:
                logger.warn(String.format(ENDPOINT_NOT_PRODUCING_FIELD, url, fieldName));

        }

    }
}


