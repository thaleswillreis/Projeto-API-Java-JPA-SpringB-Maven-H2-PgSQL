package com.homeitz.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeitz.course.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
