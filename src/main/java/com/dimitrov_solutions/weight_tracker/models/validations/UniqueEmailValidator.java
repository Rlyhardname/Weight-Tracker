package com.dimitrov_solutions.weight_tracker.models.validations;

import com.dimitrov_solutions.weight_tracker.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Autowired
    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        if (Objects.isNull(mail)) {
            throw new NullPointerException();
        }

        return !userRepository.existsByEmail(mail);
    }

}
