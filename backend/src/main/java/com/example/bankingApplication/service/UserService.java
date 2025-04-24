package com.example.bankingApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bankingApplication.model.AuthRequest;
import com.example.bankingApplication.model.User;
import com.example.bankingApplication.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public void encryptAndUpdatePasswords() {
        List<User> users = userRepository.findAll(); 
        for (User user : users) {
            String encodedPassword = passwordEncoder.encode(user.getPassword().toString().trim()); 
            System.out.println(user.getPassword().toString().trim());
            user.setPassword(encodedPassword);  
            userRepository.save(user); 
        }
    }
    
    public boolean setUserInActive(AuthRequest authRequest) {
        try {
        	User user = userRepository.findByUsername(authRequest.getUsername());
        	if(user != null) {
        		user.setStatus(false);
        		userRepository.save(user);
        		return true;
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
        
        return false;
    }
}
