package com.sergio.app.item.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergio.app.item.clients.ProductClientRest;
import com.sergio.app.item.models.Item;
import com.sergio.app.commons.models.entities.Product;
import com.sergio.app.item.service.ItemService;

@Service("serviceFeign")
//@Primary  Se inyecta por defecto en el Autowired
public class ItemServiceFeign implements ItemService{
	@Autowired
	private ProductClientRest clientFeign;

	@Override
	public List<Item> findAll() {
		return clientFeign.getAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	
	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(clientFeign.get(id), quantity);
	}


	@Override
	public Product save(Product product) {
		return clientFeign.create(product);
	}


	@Override
	public Product update(Product product, Long id) {
		return clientFeign.update(product, id);
	}


	@Override
	public void delete(Long id) {
		clientFeign.delete(id);
	}

	
}
