package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("email1", "Pedro");
		user1.setPassword("123456");
		User user2 = new User("email2", "Lucas");
		user2.setPassword("123456");
		User user3 = new User("email3", "María");
		user3.setPassword("123456");
		User user4 = new User("email4", "Marta");
		user4.setPassword("123456");
		User user5 = new User("email5", "Pelayo");
		user5.setPassword("123456");
		User user6 = new User("email6", "Edward");
		user6.setPassword("123456");

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}
