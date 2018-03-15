package com.uniovi.services;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

	String[] roles = {"ROLE_STUDENT","ROLE_ADMIN"};
	public String[] getRoles() {
	return roles;
	}

}
