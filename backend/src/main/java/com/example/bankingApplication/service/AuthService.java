package com.example.bankingApplication.service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bankingApplication.dto.LoginResponse;
import com.example.bankingApplication.dto.UserResponse;
import com.example.bankingApplication.model.AuthRequest;
import com.example.bankingApplication.model.Transaction;
import com.example.bankingApplication.model.User;
import com.example.bankingApplication.repository.UserRepository;
import com.example.bankingApplication.security.JwtUtil;

@Service
public class AuthService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;

	public LoginResponse authenticate(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername());
        if (user != null 
        		&& passwordEncoder.matches(authRequest.getPassword().toString().trim(),user.getPassword().toString())) {
        	
        	if (user.getStatus() == false) {
                throw new RuntimeException("User is not active.");
            }
        	
        	String token = jwtUtil.generateToken(user.getUsername());
        	
        	List<Transaction> sortedTransactions =  user.getTransactions().stream()
        		    .sorted(Comparator.comparing(Transaction::getDate).reversed())  // Sort by date descending
        		    .collect(Collectors.toList());
        	
        	
        	UserResponse userResponse = new UserResponse(
                    user.getName(),
                    sortedTransactions,
                    user.getInterestRate(),
                    user.getPassword(),
                    user.getUsername(),
                    user.getCurrency(),
                    user.getLocale()
            );
        	
        	return new LoginResponse(token, userResponse);
        } else {
            return null; 
        }
    }
}
