package com.dimitrov_solutions.weight_tracker.services;

import com.dimitrov_solutions.weight_tracker.models.user.UserDto;
import com.dimitrov_solutions.weight_tracker.models.weather.beans.HttpClientFacadeService;
import com.dimitrov_solutions.weight_tracker.models.weather.dto.DetailsDto;
import com.dimitrov_solutions.weight_tracker.models.weather.mapper.DetailsDtoMapperService;
import com.dimitrov_solutions.weight_tracker.models.weather.utils.UrlFacade;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final DetailsDtoMapperService detailsDtoMapperService;

    private final HttpClientFacadeService httpClientFacadeService;
    private final UserService userService;

    public WeatherService(DetailsDtoMapperService detailsDtoMapperService, HttpClientFacadeService httpClientFacadeService, UserService userService) {
        this.detailsDtoMapperService = detailsDtoMapperService;
        this.httpClientFacadeService = httpClientFacadeService;
        this.userService = userService;
    }

    public DetailsDto sendRequestReturnDetailsDto(String email) {
        UserDto dto = userService.fetchUserDto(email);

        String url = UrlFacade.createUrl(dto.getCountry(), dto.getCity());

        String body = httpClientFacadeService.send(url);

        return detailsDtoMapperService.mapTo(body, url);
    }


}
