package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import application.model.User;
import application.service.UserService;

@RestController
public class UserController {
@Autowired
private UserService userService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getProduct() {
		return userService.getUsers();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.getUsers(id),HttpStatus.OK);
	}
	
	 @PreAuthorize("hasAnyRole('ADMIN')")
	 @RequestMapping(value = "/users", method = RequestMethod.POST)
	   public ResponseEntity<Object> createUser(@RequestBody User user) {
	      return userService.save(user);
	   }
	 
	 @PreAuthorize("hasAnyRole('ADMIN')")
	 @RequestMapping(value = "/users", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateUser(@RequestBody User user) {
		  return userService.modify(user);
	   }
	 
	 @PreAuthorize("hasAnyRole('ADMIN')")
	 @RequestMapping(value = "/users", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> deleteUser(@RequestBody User user) {
		  return userService.delete(user);
	   }
	 
	 @RequestMapping(value = "/users/login", method = RequestMethod.POST)
	   public ResponseEntity<Object> loginUser(@RequestBody User user) {
	      return userService.login(user.getEmail(), user.getPassword());
	   }
}
