package com.homeitz.course.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homeitz.course.entities.User;
import com.homeitz.course.services.UserService;

	//controlador Rest com caminho do end point "/users"
@RestController
public class UserResource {

	@Autowired
	private UserService service;

		// retorna todos os usuarios pelo metodo get
	@GetMapping
	@RequestMapping("/users")
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			for (User user : list) {
				long id = user.getId();
				user.add(linkTo(methodOn(UserResource.class).findById(id)).withSelfRel());
			}
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
	}

	// retorna um usuario especifico pelo id
	@GetMapping("/users/{id}")
	public ResponseEntity<User> findById(@PathVariable(value = "id") long id) {
		Optional<User> obj = Optional.ofNullable(service.findById(id));
		if (!obj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			obj.get().add(linkTo(methodOn(UserResource.class).findAll()).withRel("Lista de Usuários"));
			return new ResponseEntity<User>(obj.get(), HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
			// var do tipo URI que recebe o id do obj e converte para o seu tipo URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);

	}

		// deleta um usuario especifico pelo id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

		// atualiza um usuário a partir do id
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
