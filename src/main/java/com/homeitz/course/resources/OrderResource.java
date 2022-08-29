package com.homeitz.course.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeitz.course.entities.Order;
import com.homeitz.course.services.OrderService;

@RestController
public class OrderResource {

	@Autowired
	private OrderService service;

	@GetMapping
	@RequestMapping(value = "/orders")
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Order order : list) {
				long id = order.getId();
				order.add(linkTo(methodOn(OrderResource.class).findById(id)).withSelfRel());
			}
			return new ResponseEntity<List<Order>>(list, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/orders/{id}")
	public ResponseEntity<Order> findById(@PathVariable(value = "id") long id) {
		Optional<Order> obj = Optional.ofNullable(service.findById(id));
		if (!obj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.get().add(linkTo(methodOn(OrderResource.class).findAll()).withRel("Lista de Pedidos"));
			return new ResponseEntity<Order>(obj.get(), HttpStatus.OK);
		}
	}
}
