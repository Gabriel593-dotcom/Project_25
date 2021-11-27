package com.example.project_25.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_25.entities.Product;
import com.example.project_25.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product finById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.get();
	}
}
