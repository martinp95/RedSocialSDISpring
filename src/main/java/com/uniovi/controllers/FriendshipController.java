package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.Friend;
import com.uniovi.entities.User;
import com.uniovi.services.FriendsService;
import com.uniovi.services.UsersService;

@Controller
public class FriendshipController {
	
	@Autowired
	private FriendsService friendshipsService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/friendship/listFriendship")
	public String getListado(Model model, Pageable pageable, Principal principal) {
		Page<Friend> friendships = new PageImpl<Friend>(new LinkedList<Friend>());
		
		String email = principal.getName();
		User user = usersService.getUserByEmail(email);
		
		friendships = friendshipsService.getFriendshipForUser(pageable, user);
		
		model.addAttribute("friendships", friendships.getContent());
		model.addAttribute("page", friendships);
		return "/friendship/listFriendship";
	}
}
