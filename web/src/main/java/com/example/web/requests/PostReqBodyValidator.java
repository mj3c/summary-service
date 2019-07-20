package com.example.web.requests;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PostReqBodyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PostReqBody.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PostReqBody postReqBody = (PostReqBody) o;
        if (postReqBody.getText() == null) {
            errors.rejectValue("text", "field.missing", "This field is required");
        } else if (postReqBody.getText().trim().isEmpty()) {
            errors.rejectValue("text", "text.empty", "Value must not be an empty string");
        }
        if (postReqBody.getLength() == null) {
            errors.rejectValue("length", "field.missing", "This field is required");
        } else {
            try {
                int length = Integer.parseInt(postReqBody.getLength());
                if (length <= 0) {
                    errors.rejectValue("length", "number.negativeOrZero", "Value must be positive");
                }
            } catch (NumberFormatException e) {
                errors.rejectValue("length", "number.format", "Value must be an integer");
            }
        }
    }
}
