package com.example.bankingApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingApplication.dto.LoginResponse;
import com.example.bankingApplication.dto.UserResponse;
import com.example.bankingApplication.model.AuthRequest;
import com.example.bankingApplication.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody AuthRequest authRequest) {
		try {
			LoginResponse loginResponse = authService.authenticate(authRequest);
			if (loginResponse != null) {
				return ResponseEntity.ok().header("Content-Type", "application/json").body(loginResponse);
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User or Password");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}