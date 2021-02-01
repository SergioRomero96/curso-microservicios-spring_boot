package com.sergio.app.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergio.app.commons.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
