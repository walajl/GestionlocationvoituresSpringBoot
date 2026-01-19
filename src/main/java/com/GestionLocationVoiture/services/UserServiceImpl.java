package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.UserDTO;
import com.GestionLocationVoiture.entities.User;
import com.GestionLocationVoiture.mapper.UserMapper;
import com.GestionLocationVoiture.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDTO saveUtilisateur(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        // Password is already set by mapper
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }
    
    @Override
    public UserDTO updateUtilisateur(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        // Password is already set by mapper
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }
    
    @Override
    public void deleteUtilisateur(UserDTO dto) {
        User user = UserMapper.toEntity(dto);
        userRepository.delete(user);
    }
    
    @Override
    public void deleteUtilisateurById(Long id) {
        userRepository.deleteById(id);
    }
    
    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).get();
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? UserMapper.toDTO(user) : null;
    }
    
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getAllUtilisateurs() {
        return getAllUsers();
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) return false;
        
        // Simple plain text comparison
        return password != null && password.equals(user.getPassword());
    }
}
