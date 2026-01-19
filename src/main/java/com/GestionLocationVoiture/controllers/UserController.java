package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.UserDTO;
import com.GestionLocationVoiture.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUtilisateurs();
    }
    
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }
    
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO dto) {
        return userService.saveUtilisateur(dto);
    }
    
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        dto.setId(id);
        return userService.updateUtilisateur(dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUtilisateurById(id);
    }
}
