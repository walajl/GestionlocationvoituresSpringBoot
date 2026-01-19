package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO saveUtilisateur(UserDTO dto);
    UserDTO updateUtilisateur(UserDTO dto);
    void deleteUtilisateur(UserDTO dto);
    void deleteUtilisateurById(Long id);
    UserDTO getUser(Long id);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUsers();
    List<UserDTO> getAllUtilisateurs();
    boolean validateCredentials(String email, String password);
}
