package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Role;
import com.uniovi.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void addRole(Role role){
		roleRepository.save(role);
	}
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}

}
