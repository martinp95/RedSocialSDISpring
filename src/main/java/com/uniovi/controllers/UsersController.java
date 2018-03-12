package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.entities.types.RoleType;
import com.uniovi.services.RoleService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("/user/listUsuarios")
	public String getListado(Model model, @RequestParam(value = "", required = false) String searchText,
			Pageable pageable, Principal principal) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		
		if (searchText != null && !searchText.isEmpty()) {
			users = usersService.searchUsersByNameAndEmail(pageable, searchText, user.getId());
		} else {
			users = usersService.findAll(pageable, user.getId());
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/listUsuarios";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") @Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.getRoles().add(roleService.findRole(RoleType.ROLE_USER.name()));
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:/user/listUsuarios";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model, Pageable pageable) {
		return "home";
	}
	
}
