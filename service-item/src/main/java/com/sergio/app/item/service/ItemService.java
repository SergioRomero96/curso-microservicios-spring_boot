package com.sergio.app.item.service;

import java.util.List;

import com.sergio.app.item.models.Item;
import com.sergio.app.commons.models.entities.Product;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long id, Integer quantity);
	public Product save(Product product);
	public Product update(Product product, Long id);
	public void delete(Long id);
}