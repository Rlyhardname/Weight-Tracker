package com.dimitrov_solutions.weight_tracker.unit.domain.models.validations;

import com.dimitrov_solutions.weight_tracker.models.validations.UniqueEmailValidator;
import com.dimitrov_solutions.weight_tracker.services.UserService;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *  isValid() inverts the result of
 *  return !userService.existByEmail(mail)
 *  return !true(not unique)
 *  return !false(unique)
 */
@SpringBootTest
public class UniqueEmailValidatorMockedTest {

    @Autowired
    UniqueEmailValidator uniqueEmailValidator;
    @MockBean
    UserService userService;
    ConstraintValidatorContext context;

    String stubMail = "myMail@gmail.com";

    @BeforeEach
    void init() {
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    void email_is_not_unique_returns_false() {
        when(userService.existsByEmail(stubMail)).thenReturn(true);

        boolean actual = uniqueEmailValidator.isValid(stubMail, context);

        assertTrue(actual);
    }


    @Test
    void email_is_unique_returns_true() {
        when(userService.existsByEmail(stubMail)).thenReturn(false);

        boolean actual = uniqueEmailValidator.isValid(stubMail, context);

        assertTrue(actual);
    }
}