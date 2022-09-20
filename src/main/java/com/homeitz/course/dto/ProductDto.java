package com.homeitz.course.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.homeitz.course.entities.Product;

public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Long id;
	@NotBlank
	@Size(max = 128)
	private String name;
	@NotBlank
	@Size(max = 256)
	private String description;
	@NotBlank
	private Double price;
	@NotBlank
	@Size(max = 128)
	private String imgUrl;
	
	public ProductDto() {
	}
	
	public ProductDto(Product obj) {
		id = obj.getId();
		name = obj.getName();
		description = obj.getDescription();
		price = obj.getPrice();
		imgUrl = obj.getImgUrl();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
