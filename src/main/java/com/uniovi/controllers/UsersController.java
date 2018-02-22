package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
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

	/*@RequestMapping("/user/listUsuarios")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/listUsuarios";
	}*/
	
	@RequestMapping("/user/listUsuarios")
	public String getListado(Model model, @RequestParam(defaultValue="0") int page) {
		model.addAttribute("usersList", usersService.
				findAll(new PageRequest(page, 5)));
		model.addAttribute("currentPage", page);
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
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:user/listUsuarios";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		/*
		 * Authentication auth =
		 * SecurityContextHolder.getContext().getAuthentication(); String dni =
		 * auth.getName(); User activeUser = usersService.getUserByDni(dni);
		 * model.addAttribute("markList", activeUser.getMarks());
		 */
		return "home";
	}
}
