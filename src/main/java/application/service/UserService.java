package application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import application.dao.UserRepository;
import application.model.AppResponse;
import application.model.ResponseCodes;
import application.model.User;
import application.util.PasswordHash;

@Service
public class UserService {
@Autowired
private UserRepository repository;

	public Page<User> getUsers(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize,Sort.by("name"));
		return repository.findAll(pageable);
	}
	
	public Optional<User> getUsers(Long id) {
		return repository.findById(id);
	}
	
	
	public ResponseEntity<Object> save(List<User> userList) {
		AppResponse response = new AppResponse();
		for(User user: userList){
			if(user.getPassword().equals("") || user.getPassword() == null) {
				user.setPassword(PasswordHash.generateHash(user.getName()));
			}
			repository.save(user);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Object> modify(User user) {
		
		repository.save(user);
		AppResponse response = new AppResponse();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Object> delete(Long id) {
		Optional<User> user = repository.findById(id);
		repository.delete(user.get());
		return new ResponseEntity<>("User Deleted successfully", HttpStatus.CREATED);
	}
	
	public ResponseEntity<Object> login(String username, String password) {
		Optional<User> user = repository.findByName(username);
		AppResponse response = new AppResponse();
		if(user.isPresent()) {
			if(user.get().getPassword().equals(PasswordHash.generateHash(password))) {
				response.setResponseCode(ResponseCodes.SUCCESS);
				response.setResponseValue(user.get().getPassword());
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				response.setResponseCode(ResponseCodes.ERROR_INVALID_PASSWORD);
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
		}else {
			response.setResponseCode(ResponseCodes.ERROR_INVALID_USERNAME);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}
	
}
