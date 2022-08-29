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

import com.homeitz.course.entities.Product;
import com.homeitz.course.services.ProductService;

@RestController
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	@RequestMapping("/products")
	public ResponseEntity<List<Product>> findAll() {
		List<Product> list = service.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Product product : list) {
				long id = product.getId();
				product.add(linkTo(methodOn(ProductResource.class).findById(id)).withSelfRel());
			}
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> findById(@PathVariable(value = "id") long id) {
		Optional<Product> obj = Optional.ofNullable(service.findById(id));
		if (!obj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.get().add(linkTo(methodOn(ProductResource.class).findAll()).withRel("Lista de Produtos"));
			return new ResponseEntity<Product>(obj.get(), HttpStatus.OK);
		}
	}
}
