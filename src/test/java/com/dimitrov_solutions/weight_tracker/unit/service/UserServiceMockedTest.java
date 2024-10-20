package com.dimitrov_solutions.weight_tracker.unit.service;

import com.dimitrov_solutions.weight_tracker.models.user.entity.User;
import com.dimitrov_solutions.weight_tracker.repositories.UserRepository;
import com.dimitrov_solutions.weight_tracker.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserServiceMockedTest {
    @MockBean
    UserRepository userRepository;
    UserService userService;
    String stubEmail;
    String stubPassword;

    @BeforeEach
    void init() {
        stubEmail = "myMail@gmail.com";
        stubPassword = "myPass";
    }

    @Test
    void Fetch_user_account_by_email() {
        User expected = new User(stubEmail, stubPassword);
        //    when(userService.fetchUserByEmail()).
    }
}
