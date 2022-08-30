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

import com.homeitz.course.entities.Category;
import com.homeitz.course.services.CategoryService;

@RestController
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	@RequestMapping(value = "/categories")
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (Category category : list) {
				long id = category.getId();
				category.add(linkTo(methodOn(CategoryResource.class).findById(id)).withSelfRel());
			}
			return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/categories/{id}")
	public ResponseEntity<Category> findById(@PathVariable(value = "id") long id) {
		Optional<Category> obj = Optional.ofNullable(service.findById(id));
		if (!obj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.get().add(linkTo(methodOn(CategoryResource.class).findAll()).withRel("Lista de Categorias"));
			return new ResponseEntity<Category>(obj.get(), HttpStatus.OK);
		}
	}
}
