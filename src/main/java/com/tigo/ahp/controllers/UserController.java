package com.tigo.ahp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tigo.ahp.dtos.SignupRequest;
import com.tigo.ahp.dtos.UserDTO;
import com.tigo.ahp.dtos.UserUpdateRequest;
import com.tigo.ahp.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService; 

    @CrossOrigin(origins = "*")
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest userUpdateRequest, 
                                        @RequestHeader("Authorization") String authorizationHeader) {


        String jwt = authorizationHeader.replace("Bearer ", ""); 

        String userIdFromJwt = extractUserIdFromJwt(jwt); 

        if (!userUpdateRequest.getEmail().equals(userIdFromJwt)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }

        try {
          UserDTO updatedUser = userService.updateUser(userId, userUpdateRequest); 
          return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // Helper method to extract user ID from JWT 
    private String extractUserIdFromJwt(String jwt) {
        // ... (Implement logic to extract user ID from JWT claims)
        // ... (Example using JWT library)
        Claims claims = Jwts.parserBuilder()
                .setSigningKey("5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437") // Replace with your actual secret key
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject(); 
    }

}