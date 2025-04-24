package com.example.bankingApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingApplication.model.AuthRequest;
import com.example.bankingApplication.model.User;
import com.example.bankingApplication.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    
    @GetMapping("/encrypt-passwords")
    public String encryptPasswords() {
    	System.out.println("Hello WOrld");
        userService.encryptAndUpdatePasswords();  // Call the service to update passwords
        return "Passwords encrypted and updated successfully!";
    }
    
    
    @PostMapping("/accountClose")
    public ResponseEntity<Integer> setUserInActive(@RequestBody AuthRequest authRequest) {
       try {
    	   Boolean isUserActiveNow = userService.setUserInActive(authRequest);
    	   if(isUserActiveNow) {
    		   return ResponseEntity.status(HttpStatus.OK).body(1);
    	   }
       }catch(Exception e) {
    	   e.printStackTrace();
    	   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
       
       return ResponseEntity.status(HttpStatus.OK).body(0);
    }
}
