package com.uniovi.repository;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.model.User;

public interface UserRepository extends CrudRepository<User,Long> {

}
