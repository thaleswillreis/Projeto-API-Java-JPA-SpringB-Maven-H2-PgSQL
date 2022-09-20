package com.homeitz.course.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.homeitz.course.entities.User;
import com.homeitz.course.repositories.UserRepository;
import com.homeitz.course.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository repository;
	
	@Test
	public void findByIdTest() throws ResourceNotFoundException {
		User user = userService.findById(1L);
		assertEquals("Maria", user.getName());
		assertEquals("maria@gmail.com", user.getEmail());
		assertEquals("988888888", user.getPhone());
		assertEquals("123456", user.getPassword());
	}
	
	@Test
	public void deleteTest() {
		userService.delete(1L);
		Optional<User> user = repository.findById(1L);
		assertFalse(user.isPresent());
	}
}
