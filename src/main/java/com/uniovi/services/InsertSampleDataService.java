package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private PublicationService postsService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
	User user1 = new User("email1@example.com", "Pedro");
	user1.setPassword("123456");
	user1.setRole(roleService.getRoles()[0]);
	User user2 = new User("email2@example.com", "Lucas");
	user2.setPassword("123456");
	user2.setRole(roleService.getRoles()[0]);
	User user3 = new User("email3@example.com", "María");
	user3.setPassword("123456");
	user3.setRole(roleService.getRoles()[0]);
	User user4 = new User("email4@example.com", "Marta");
	user4.setPassword("123456");
	user4.setRole(roleService.getRoles()[0]);
	User user5 = new User("email5@example.com", "Pelayo");
	user5.setPassword("123456");
	user5.setRole(roleService.getRoles()[0]);
	User user6 = new User("email6@example.com", "Edward");
	user6.setPassword("123456");
	user6.setRole(roleService.getRoles()[0]);
	User user7 = new User("email7@example.com", "Luis");
	user7.setPassword("123456");
	user7.setRole(roleService.getRoles()[0]);
	User user8 = new User("email8@example.com", "Juan");
	user8.setPassword("123456");
	user8.setRole(roleService.getRoles()[0]);
	User user9 = new User("email9@example.com", "Marcela");
	user9.setPassword("123456");
	user9.setRole(roleService.getRoles()[0]);
	User user10 = new User("email10@example.com", "Marina");
	user10.setPassword("123456");
	user10.setRole(roleService.getRoles()[0]);
	User user11 = new User("email11@example.com", "Marina");
	user11.setPassword("123456");
	user11.setRole(roleService.getRoles()[1]);

	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
	usersService.addUser(user4);
	usersService.addUser(user5);
	usersService.addUser(user6);

	usersService.addUser(user7);
	usersService.addUser(user8);
	usersService.addUser(user9);
	usersService.addUser(user10);
	usersService.addUser(user11);

	Publication post1 = new Publication("Publicación de prueba (user1)", "Esto es una prueba", user1);
	Publication post2 = new Publication("Publicación de prueba (user2)", "Esto es una prueba", user2);
	postsService.addPublication(post1);
	postsService.addPublication(post2);
    }
}
