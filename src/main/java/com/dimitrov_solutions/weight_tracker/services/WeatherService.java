package com.dimitrov_solutions.weight_tracker.services;

import com.dimitrov_solutions.weight_tracker.models.dto.UserDto;
import com.dimitrov_solutions.weight_tracker.models.dto.WeatherDetailsDto;
import com.dimitrov_solutions.weight_tracker.weather.mapper.DetailsDtoMapper;
import com.dimitrov_solutions.weight_tracker.weather.network.HttpClientFacadeService;
import com.dimitrov_solutions.weight_tracker.weather.network.UrlFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final DetailsDtoMapper detailsDtoMapper;

    private final HttpClientFacadeService httpClientFacadeService;
    private final UserService userService;

    public WeatherService(DetailsDtoMapper detailsDtoMapper, HttpClientFacadeService httpClientFacadeService, UserService userService) {
        this.detailsDtoMapper = detailsDtoMapper;
        this.httpClientFacadeService = httpClientFacadeService;
        this.userService = userService;
    }

    public WeatherDetailsDto sendRequestReturnDetailsDto(String email) {
        UserDto dto = userService.fetchUserDto(email);

        String url = UrlFacade.createUrl(dto.getCountry(), dto.getCity());

        String body = httpClientFacadeService.send(url);

        return detailsDtoMapper.mapTo(body, url);
    }


}
