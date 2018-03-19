package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.FriendshipRequest;
import com.uniovi.entities.User;

public interface FriendshipRequestRepository extends CrudRepository<FriendshipRequest, Long> {

    // El user2 recibe las peticiones del user1
    @Query("SELECT r FROM FriendshipRequest r WHERE r.user2 = ?1")
    Page<FriendshipRequest> findAllByUser(Pageable pageable, User user);

    @Query("SELECT r FROM FriendshipRequest r where r.user1 = ?1 and r.user2 = ?2")
    FriendshipRequest findByUsers(User user1, User user2);

    @Query("SELECT r FROM FriendshipRequest r WHERE r.user2.id = ?1")
    List<FriendshipRequest> findAllByUser(Long id);
}
