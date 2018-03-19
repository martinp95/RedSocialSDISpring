package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Component
public class AdminLoginFormValidator implements Validator {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean supports(Class<?> aClass) {
	return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
	User user = (User) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Error.empty");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
	User admin = usersService.getUserByEmail(user.getEmail());
	if (admin == null) {
	    errors.rejectValue("email", "Error.adminLogin.usuarioContrasenaIncorrectos");
	} else {
	    if (!admin.getRole().equals("ROLE_ADMIN")) {
		errors.rejectValue("role", "Error.adminLogin.adminRole");
	    }
	}
    }
}
