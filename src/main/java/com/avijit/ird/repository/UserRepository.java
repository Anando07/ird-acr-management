package com.avijit.ird.repository;

import com.avijit.ird.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Page<User> findAll(Pageable page);

    User findByUserId(String userId);

    List<User> findAllByUsernameNotLike(String username);

}