package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.UserDTO;
import com.GestionLocationVoiture.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email").trim();
        String password = loginRequest.get("password").trim();
        
        boolean isValid = userService.validateCredentials(email, password);
        
        if (!isValid) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email ou mot de passe incorrect");
            return ResponseEntity.status(401).body(error);
        }
        
        UserDTO user = userService.getUserByEmail(email);
        
        // Simple login response
        Map<String, Object> response = new HashMap<>();
        response.put("token", "simple-token-" + user.getId());
        response.put("user", user);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        // SECURITY: Always force CLIENT role for public registration
        // Admin accounts must be created by existing admins via /users endpoint
        userDTO.setRole("CLIENT");
        
        // Validate required fields
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "L'email est requis");
            return ResponseEntity.badRequest().body(error);
        }
        if (userDTO.getMotDePasse() == null || userDTO.getMotDePasse().isBlank()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Le mot de passe est requis");
            return ResponseEntity.badRequest().body(error);
        }
        
        // Check if email already exists
        UserDTO existing = userService.getUserByEmail(userDTO.getEmail());
        if (existing != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Cet email est déjà utilisé");
            return ResponseEntity.status(409).body(error);
        }
        
        UserDTO savedUser = userService.saveUtilisateur(userDTO);
        return ResponseEntity.ok(savedUser);
    }
}
