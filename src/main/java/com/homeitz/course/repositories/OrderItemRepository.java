package com.homeitz.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeitz.course.entities.OrderItem;


public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
