package com.sergio.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sergio.app.commons.models.entities.Product;

@FeignClient(name="service-products")
public interface ProductClientRest {
	
	@GetMapping
	public List<Product> getAll();
	
	@GetMapping("/{id}")
	public Product get(@PathVariable Long id);
	
	@PostMapping
	public Product create(@RequestBody Product product);
	
	@PutMapping("/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id);
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id);
}
