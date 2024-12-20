package com.dimitrov_solutions.weight_tracker.unit.domain.models.validations;

import com.dimitrov_solutions.weight_tracker.models.validations.UniqueEmailValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UniqueEmailValidatorTest {

    @Autowired
    UniqueEmailValidator uniqueEmailValidator;

    @Test
    void unique_email_returns_true() {
        String email = "thisEmailisunique@unique.com";

        boolean actual = uniqueEmailValidator.isValid(email, null);

        assertTrue(actual);
    }

    @Test
    void existing_emails_returns_false() {
        String email1 = "firstemail@gmail.com";
        String email2 = "second@gmail.com";
        String email3 = "3rdEntered5thShow@gmail.com";
        String email4 = "4rdEntered3thShow@gmail.com";
        String email5 = "5rdEntered4thShow@gmail.com";

        boolean first = uniqueEmailValidator.isValid(email1, null);
        boolean second = uniqueEmailValidator.isValid(email2, null);
        boolean third = uniqueEmailValidator.isValid(email3, null);
        boolean forth = uniqueEmailValidator.isValid(email4, null);
        boolean fifth = uniqueEmailValidator.isValid(email5, null);

        assertAll(() -> {
            assertFalse(first);
            assertFalse(second);
            assertFalse(third);
            assertFalse(forth);
            assertFalse(fifth);
        });

    }

    @Test
    void null_value_return_EmailTakenException() {
        String email = null;

        assertThrows(NullPointerException.class, () -> uniqueEmailValidator.isValid(email, null));
    }
}