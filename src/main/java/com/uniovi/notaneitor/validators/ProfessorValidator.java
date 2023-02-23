package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.ProfessorsService;
import com.uniovi.notaneitor.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfessorValidator implements Validator {

    @Autowired
    private ProfessorsService professorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Professor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");
        if(!Character.isLetter(professor.getDni().charAt(professor.getDni().length()-1))) {
            errors.rejectValue("dni", "Error.user.dniLetter");
        }
        if(professor.getDni().length()!=9) {
            errors.rejectValue("dni", "Error.user.dniLength");
        }
        if (professorService.getProfessor(professor.getDni()) != null) {
            errors.rejectValue("dni", "Error.user.dniDuplicate");
        }
    }

}
