package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;
import com.uniovi.services.FriendshipRequestService;
import com.uniovi.services.UsersService;

@Controller
public class FriendshipRequestController {
	
	@Autowired
	private FriendshipRequestService friendshipRequestService;
	
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/friendshipRequest/add/{id}")
	public String getMark(@PathVariable Long id, Principal principal) {
		String email = principal.getName();
		User user1 = usersService.getUserByEmail(email);
		
		User user2 = usersService.getUser(id);
		
		FriendshipRequest original = new FriendshipRequest(user1, user2);
				
		friendshipRequestService.addFriendshipRequest(original);
		return "redirect:/user/listUsuarios";
	}
	
}
