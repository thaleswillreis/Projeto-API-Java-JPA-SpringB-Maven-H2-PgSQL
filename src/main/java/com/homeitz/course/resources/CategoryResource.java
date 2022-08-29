package com.homeitz.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeitz.course.entities.Category;
import com.homeitz.course.services.CategoryService;

	//controlador Rest com caminho do end point "/categories"
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
		// retorna todos as categorias pelo metodo get
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {		
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
		// retorna uma categoria especifica pelo id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
