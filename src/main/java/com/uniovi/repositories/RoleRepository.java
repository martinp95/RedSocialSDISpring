package com.uniovi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniovi.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findRoleByName(String name);

}
