package com.example.myapp.controller;

import com.example.myapp.model.User;
import com.example.myapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void testGetAllUsers() throws Exception {
		User user1 = new User(1L, "Alice", "alice@example.com");
		User user2 = new User(2L, "Bob", "bob@example.com");
		
		given(userService.getAllUsers()).willReturn(Arrays.asList(user1, user2));
		
		mockMvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name").value("Alice"))
			.andExpect(jsonPath("$[1].name").value("Bob"));
	}
	
	@Test
	public void testGetUserById_UserExists() throws Exception {
		User user = new User(1L, "Alice", "alice@example.com");
		given(userService.getUserById(1L)).willReturn(Optional.of(user));
		
		mockMvc.perform(get("/users/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("Alice"));
	}
	
	@Test
	public void testGetUserById_UserNotFound() throws Exception {
		given(userService.getUserById(1L)).willReturn(Optional.empty());
		
		mockMvc.perform(get("/users/1"))
			.andExpect(status().isNotFound());
	}
}
