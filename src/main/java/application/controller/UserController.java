package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getProduct() {
		return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProduct(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.getUsers(id),HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/users", method = RequestMethod.POST)
	   public ResponseEntity<Object> createUser(@RequestBody User user) {
	      return userService.save(user);
	   }
	 
	 @RequestMapping(value = "/users", method = RequestMethod.PUT)
	   public ResponseEntity<Object> updateUser(@RequestBody User user) {
		  return userService.modify(user);
	   }
	 
	 @RequestMapping(value = "/users", method = RequestMethod.DELETE)
	   public ResponseEntity<Object> deleteUser(@RequestBody User user) {
		  return userService.delete(user);
	   }
}
