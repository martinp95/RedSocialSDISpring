package com.uniovi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.services.PublicationService;
import com.uniovi.services.UsersService;

@Controller
public class PublicationController {

	@Autowired
	private PublicationService publicationService;

	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/publication/add", method = RequestMethod.POST)
	public String setPublication(@ModelAttribute Publication publication, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		Publication original = new Publication(publication.getTitle(), publication.getTexto(), user);
		publicationService.addPublication(original);
		return "redirect:/publication/listPosts";
	}

	@RequestMapping(value = "/publication/add")
	public String getPublication(Model model) {
		model.addAttribute("publicationList", publicationService.getPublications());
		return "publication/add";
	}

	@RequestMapping("/publication/listPosts")
	public String getListado(Model model, Principal principal) {
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		List<Publication> posts = publicationService.getPublicationsForUser(user);
		model.addAttribute("postsList", posts);
		return "/publication/listPosts";
	}
	
	@RequestMapping("/publication/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("post", publicationService.getPublication(id));
		return "publication/details";
	}

}
