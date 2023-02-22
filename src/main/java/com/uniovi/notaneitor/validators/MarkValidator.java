package com.uniovi.notaneitor.validators;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MarkValidator implements Validator {
    @Autowired
    private MarksService marksService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Mark.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        if (mark.getDescription().length() < 20) {
            errors.rejectValue("description", "Error.add.description.length");
        }
        if(mark.getScore() < 0 || mark.getScore() > 10) {
            errors.rejectValue("score", "Error.add.mark");
        }
    }

}
