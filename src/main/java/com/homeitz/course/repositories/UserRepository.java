package com.homeitz.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeitz.course.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
