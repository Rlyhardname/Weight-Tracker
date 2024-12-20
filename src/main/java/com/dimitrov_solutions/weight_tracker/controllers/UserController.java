package com.dimitrov_solutions.weight_tracker.controllers;

import com.dimitrov_solutions.weight_tracker.exceptions.InvalidCredentialsException;
import com.dimitrov_solutions.weight_tracker.models.dto.UserDto;
import com.dimitrov_solutions.weight_tracker.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    private static final String BAD_CREDENTIALS = "Email or password is invalid";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        try {
            return ResponseEntity.ok(userService.verify(email, password));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(401).body(BAD_CREDENTIALS);
        }
    }

}