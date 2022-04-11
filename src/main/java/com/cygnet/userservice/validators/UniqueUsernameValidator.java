package com.cygnet.userservice.validators;

import com.cygnet.userservice.repositorys.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>
{

    private final UserRepository userRepository;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String name, ConstraintValidatorContext context)
    {
        return !userRepository.findByName(name).isPresent();
    }
}
