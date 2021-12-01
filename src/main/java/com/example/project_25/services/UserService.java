package com.example.project_25.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project_25.entities.User;
import com.example.project_25.repositories.UserRepository;
import com.example.project_25.services.exceptions.ResourceAlreadyExistsException;
import com.example.project_25.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException());
	}

	public void insert(User obj) {
		List<User> filteredUsers = findAll().stream().filter(u -> u.equals(obj)).collect(Collectors.toList());
		if (filteredUsers.isEmpty()) {
			userRepository.save(obj);
		} else {
			throw new ResourceAlreadyExistsException();
		}
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public User update(User userDataWithUpdatedInformation, Long id) {
		User userDataForUpdate = findById(id);
		setUpdates(userDataForUpdate, userDataWithUpdatedInformation);

		return userRepository.save(userDataForUpdate);
	}

	private void setUpdates(User userDataForUpdate, User userDataWithUpdatedInformation) {
		userDataForUpdate.setName(userDataWithUpdatedInformation.getName());
		userDataForUpdate.setPassword(userDataWithUpdatedInformation.getPassword());
		userDataForUpdate.setPhone(userDataWithUpdatedInformation.getPhone());
	}
}
