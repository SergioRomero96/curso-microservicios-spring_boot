package com.sergio.app.products.service;

import java.util.List;

import com.sergio.app.commons.models.entities.Product;

public interface IProductService {
	public List<Product> findAll();
	public Product findById(Long id);
	public Product save(Product product);
	public void deleteById(Long id);
}
