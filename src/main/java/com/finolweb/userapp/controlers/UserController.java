package com.finolweb.userapp.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.finolweb.userapp.models.User;
import com.finolweb.userapp.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(@RequestParam(value= "startWith" , required=false) String startWith){
		return new ResponseEntity<List<User>>(userService.getUsers(startWith), HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{username}")
	public ResponseEntity<User> getUsersByUsername(@PathVariable("username") String username){
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.createUser(user) ,  HttpStatus.CREATED );
	}

	
	@PutMapping(value="/{username}")
	public ResponseEntity<User> updateUser(@PathVariable("username") String username,  @RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(user, username) ,  HttpStatus.OK );
	}
	
	
	@DeleteMapping(value="/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String usernamer){
		userService.deleteUser(usernamer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
