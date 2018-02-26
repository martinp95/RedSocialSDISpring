package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publication;
import com.uniovi.services.PublicationService;

@Controller
public class PublicationController {
	
	@Autowired
	private PublicationService publicationService;
	
	@RequestMapping(value = "/publication/add", method = RequestMethod.POST)
	public String setPublication(@ModelAttribute Publication publication) {
		publicationService.addPublication(publication);
		return "publication/add";
		//return "redirect:/publication/list";
	}

	@RequestMapping(value = "/publication/add")
	public String getMark(Model model) {
		model.addAttribute("publicationList", publicationService.getPublications());
		return "publication/add";
	}

}
