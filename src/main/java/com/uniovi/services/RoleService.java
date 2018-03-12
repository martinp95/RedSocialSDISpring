package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Role;
import com.uniovi.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public void addRole(Role role) {
		roleRepository.save(role);
	}
	
	public Role findRole(String name){
		return roleRepository.findRoleByName(name);
	}

}
