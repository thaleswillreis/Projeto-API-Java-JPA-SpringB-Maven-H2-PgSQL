package com.homeitz.course.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.homeitz.course.entities.User;

public class UserDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank
	@Size(max = 60)
	private String name;
	@NotBlank
	@Size(max = 60)
	@Email
	private String email;
	@NotBlank
	@Size(max = 14)
	private String phone;
	@NotBlank
	private String password;
	
	public UserDto() {
	}
	
	public UserDto(User obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		phone = obj.getPhone();
		password = obj.getPassword();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
