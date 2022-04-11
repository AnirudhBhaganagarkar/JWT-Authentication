package com.cygnet.userservice.validators;

import com.cygnet.userservice.repositorys.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckMailIDValidator implements ConstraintValidator<CheckMailID, String>
{

    private final UserRepository userRepository;

    public CheckMailIDValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        return !userRepository.findByEmail(mail).isEmpty();
    }
}
