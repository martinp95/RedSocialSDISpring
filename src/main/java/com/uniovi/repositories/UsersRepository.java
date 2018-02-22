package com.uniovi.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends PagingAndSortingRepository<User, Long> {
	User findByEmail(String email);

}
