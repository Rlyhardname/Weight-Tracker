package com.dimitrov_solutions.weight_tracker.models.weather.mapper;

import com.dimitrov_solutions.weight_tracker.models.weather.BadDataApiResponseException;
import com.dimitrov_solutions.weight_tracker.models.weather.beans.IconNameStateMachine;
import com.dimitrov_solutions.weight_tracker.models.weather.dto.DetailsDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DetailsDtoMapper extends JsonDeserializer<DetailsDto> implements InternalValidator {
    public static final Logger logger = LoggerFactory.getLogger(DetailsDtoMapper.class);
    public static final String ENDPOINT_NOT_PRODUCING_FIELD = "Endpoint at: %s%n doesn't contain field: %s";
    private String url;
    private IconNameStateMachine iconNameStateMachine;

    @Autowired
    public void setIconNameStateMachine(IconNameStateMachine iconNameStateMachine) {
        this.iconNameStateMachine = iconNameStateMachine;
    }

    public DetailsDto mapTo(String body, String url) {
        try {
            this.url = url;

            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();

            module.addDeserializer(DetailsDto.class, this);
            mapper.registerModule(module);

            return mapper.readValue(body, DetailsDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public final DetailsDto deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        int temp = BankerRound(validateToDouble(node.get("main").get("temp"), "main.temp"));
        int clouds = BankerRound(validateToDouble(node.get("clouds").get("all"), "clouds.all"));
        String country = validateToString(node.get("sys").get("country"), "sys.country");
        String city = validateToString(node.get("name"), "name");
        String validatedApiWeatherState = validateToString(node.get("weather").get(0).get("icon"), "weather.icon");
        String imgName = iconNameStateMachine.nameBasedOnState(validatedApiWeatherState, clouds);

        return new DetailsDto(temp, clouds, imgName, country, city);
    }

    private int BankerRound(Double value) {
        if (value == null) {
            return 0;
        }

        var decimal = new BigDecimal(value).setScale(0, RoundingMode.HALF_EVEN);
        return decimal.intValueExact();
    }


    @Override
    public final String validateToString(JsonNode node, String fieldName) {
        try {
            return node.asText();
        } catch (NullPointerException e) {
            logEndpointNotProducingField(fieldName, "info");
        }

        return "";
    }

    @Override
    public final Double validateToDouble(JsonNode node, String fieldName) {
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
    public final Integer validateToInt(JsonNode node, String fieldName) {
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


