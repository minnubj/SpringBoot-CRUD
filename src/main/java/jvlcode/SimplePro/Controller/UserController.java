package jvlcode.SimplePro.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jvlcode.SimplePro.Entity.UserEntity;
import jvlcode.SimplePro.Exception.ResourceNotFountException;
import jvlcode.SimplePro.Models.User;
import jvlcode.SimplePro.Repository.UserRespository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserRespository userRepository;
	
	//All user list
	@GetMapping
	public List<UserEntity> getUser() {
		
	/*	return Arrays.asList(
				new User(1L,"Minnu","minnu@gmail.com"),
				new User(2L,"Meenu","meenu@gmail.com"),
				new User(3L,"Aishwarya","aishu@gmail.com"));
				
				*/
		return userRepository.findAll();
	}
	
	//create user
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		return userRepository.save(user) ;
	}
	
	//get user by id and exception 
	
	@GetMapping("/{id}")
	public UserEntity GetUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFountException("User not found with this ID:"+id));
	}

	//update user
	
	@PutMapping("/{id}")
	public UserEntity updateUser( @PathVariable Long id ,@RequestBody UserEntity user) {
		UserEntity userData=userRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFountException("User not found with this ID:"+id));
		userData.setEmail(user.getEmail());
		userData.setName(user.getName());
		return userRepository.save(userData);
	}
	
	
	//Delete User
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		UserEntity userData=userRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFountException("User not found with this ID:"+id));
		userRepository.delete(userData);
		return ResponseEntity.ok().build();
	}
	
}
