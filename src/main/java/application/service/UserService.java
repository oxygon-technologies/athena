package application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.dao.UserRepository;
import application.model.User;

@Service
public class UserService {
@Autowired
private UserRepository repository;

	public List<User> getUsers() {
		return repository.findAll();
	}
	
	public Optional<User> getUsers(Long id) {
		return repository.findById(id);
	}
	
	
	public ResponseEntity<Object> save(User user) {
		repository.save(user);
		return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Object> modify(User user) {
		repository.save(user);
		return new ResponseEntity<>("User modified successfully", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Object> delete(User user) {
		repository.delete(user);
		return new ResponseEntity<>("User Deleted successfully", HttpStatus.CREATED);
	}
	
}
