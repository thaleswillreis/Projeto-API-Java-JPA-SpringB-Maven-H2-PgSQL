package com.homeitz.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeitz.course.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
