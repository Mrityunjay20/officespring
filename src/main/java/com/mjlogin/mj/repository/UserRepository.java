package com.mjlogin.mj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mjlogin.mj.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
