package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

    @Autowired
    private UsersService usersService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if(!Character.isLetter(user.getDni().charAt(user.getDni().length()-1))) {
            errors.rejectValue("dni", "Error.user.dniLetter");
        }
        if(user.getDni().length()!=9) {
            errors.rejectValue("dni", "Error.user.dniLength");
        }
        if (usersService.getUserByDni(user.getDni()) != null) {
            errors.rejectValue("dni", "Error.user.dniDuplicate");
        }

    }

}

