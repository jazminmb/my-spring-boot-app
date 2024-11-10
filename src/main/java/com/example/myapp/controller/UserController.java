package com.example.myapp.controller;

import com.example.myapp.model.User;
import com.example.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

		@Autowired
		private UserService userService;
		
		@GetMapping
		public List<User> getAllUsers() {
			return userService.getAllUsers();
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<User>getUserById(@PathVariable Long id) {
			return userService.getUserById(id)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
		}
}
