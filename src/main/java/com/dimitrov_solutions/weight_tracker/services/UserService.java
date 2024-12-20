package com.dimitrov_solutions.weight_tracker.services;

import com.dimitrov_solutions.weight_tracker.exceptions.InvalidCredentialsException;
import com.dimitrov_solutions.weight_tracker.models.dto.UserDto;
import com.dimitrov_solutions.weight_tracker.models.NoSuchUserException;
import com.dimitrov_solutions.weight_tracker.models.entity.User;
import com.dimitrov_solutions.weight_tracker.repositories.UserRepository;
import com.dimitrov_solutions.weight_tracker.security.JwtService;
import com.dimitrov_solutions.weight_tracker.weather.beans.MapOfCountryCodesAndCapitals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final MapOfCountryCodesAndCapitals mapOfCountryCodesAndCapitals;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public UserService(MapOfCountryCodesAndCapitals mapOfCountryCodesAndCapitals, PasswordEncoder passwordEncoder, AuthenticationManager authManager, UserRepository userRepository, JwtService jwtService) {
        this.mapOfCountryCodesAndCapitals = mapOfCountryCodesAndCapitals;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public UserDto register(UserDto userDto) {
        User user = new User(userDto);

        if (user.getCity() == null) {
            String countryCode = userDto.getCountry().substring(0, 2);
            if (mapOfCountryCodesAndCapitals.containsCountryCode(countryCode))
                user.setCity(mapOfCountryCodesAndCapitals.getCapital(countryCode));
            else {
                logger.warn("Possible third party UI trying to use API");
                throw new IllegalArgumentException();
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return new UserDto(userRepository.save(user));
    }

    public String verify(String email, String password) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(email);
        }

        throw new InvalidCredentialsException();
    }

    public Optional<User> fetchUserByEmail(String email) {
        return Optional.of(new User());
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto fetchUserDto(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                NoSuchUserException::new
        );

        return new UserDto(user);
    }
}
