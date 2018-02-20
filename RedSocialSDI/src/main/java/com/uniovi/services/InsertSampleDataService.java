package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.model.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("email1", "Pedro", "Díaz");
		User user2 = new User("email2", "Lucas", "Núñez");
		User user3 = new User("email3", "María", "Rodríguez");
		User user4 = new User("email4", "Marta", "Almonte");
		User user5 = new User("email5", "Pelayo", "Valdes");
		User user6 = new User("email6", "Edward", "Núñez");

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}
