package com.ajark.taskmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajark.taskmanagement.model.LoginRequest;
import com.ajark.taskmanagement.model.RegistrationRequest;
import com.ajark.taskmanagement.model.User;
import com.ajark.taskmanagement.repository.UserRepository;




@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	//private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String registerUser(RegistrationRequest request) {
		if (userRepository.findByUsername(request.getUsername()).isPresent()) {
			return "Username already exists!";
		}

		User user = new User();
		user.setUsername(request.getUsername());
		//user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPassword(request.getPassword());
		user.setEmail(request.getEmail());

		userRepository.save(user);
		return "User registered successfully!";
	}

	public String loginUser(LoginRequest request) {
		Optional<User> user = userRepository.findByUsername(request.getUsername());

		/*
		 * if (user.isPresent() && passwordEncoder.matches(request.getPassword(),
		 * user.get().getPassword())) { return "Login successful!"; }
		 */
		if(user.isPresent() && request.getPassword().equals(user.get().getPassword())) {
			return "Login successful!";
		}

		return "Invalid username or password!";
	}

}
