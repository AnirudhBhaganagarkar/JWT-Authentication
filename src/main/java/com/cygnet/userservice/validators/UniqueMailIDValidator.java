package com.cygnet.userservice.validators;

import com.cygnet.userservice.repositorys.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueMailIDValidator implements ConstraintValidator<UniqueMailID, String>
{

    private final UserRepository userRepository;

    public UniqueMailIDValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context)
    {
        return !userRepository.findByEmail(mail).isPresent();
    }
}
