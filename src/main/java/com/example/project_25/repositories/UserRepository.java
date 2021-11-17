package com.example.project_25.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project_25.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
