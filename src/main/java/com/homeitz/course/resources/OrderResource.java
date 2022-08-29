package com.homeitz.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeitz.course.entities.Order;
import com.homeitz.course.services.OrderService;

	//controlador Rest com caminho do end point "/orders"
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
		// retorna todos os "order" pelo metodo get
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {		
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
		// retorna um "order" especifico pelo id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
