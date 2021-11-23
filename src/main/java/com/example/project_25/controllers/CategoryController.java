package com.example.project_25.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project_25.entities.Category;
import com.example.project_25.services.CategoryService;

@RestController
@RequestMapping(value="/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {

		List<Category> list = categoryService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
