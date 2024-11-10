package com.example.myapp.service;

import com.example.myapp.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final List<User> users = new ArrayList<> ();
	
	public UserService() {
		users.add(new User(1L, "Alice", "alice@example.com"));
		users.add(new User(2L, "Bob", "bob@example.com"));
	}
	
	public List<User> getAllUsers() {
		return users;
	}
	
	public Optional<User> getUserById(Long id) {
		return users.stream().filter(user -> user.getId().equals(id)).findFirst();
	}
}
