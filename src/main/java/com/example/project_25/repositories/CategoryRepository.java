package com.example.project_25.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_25.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
