package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Friend;
import com.uniovi.entities.User;

public interface FriendsRepository extends CrudRepository<Friend, Long>{

	@Query("SELECT r FROM Friend r WHERE r.user2 = ?1")
	Page<Friend> findAllByUser(Pageable pageable, User user);

	@Query("SELECT r FROM Friend r WHERE r.user2.id = ?1")
	List<Friend> findAllByUser(Long id);
}
