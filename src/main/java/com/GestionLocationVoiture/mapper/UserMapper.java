package com.GestionLocationVoiture.mapper;

import com.GestionLocationVoiture.dto.UserDTO;
import com.GestionLocationVoiture.entities.User;

public class UserMapper {
    
    public static UserDTO toDTO(User entity) {
        if (entity == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        dto.setAdresse(entity.getAdresse());
        dto.setRole(entity.getRole());
        // On ne renvoie jamais le mot de passe dans le DTO pour la sécurité
        return dto;
    }
    
    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        entity.setAdresse(dto.getAdresse());
        entity.setRole(dto.getRole());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
