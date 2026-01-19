package com.GestionLocationVoiture.mapper;

import com.GestionLocationVoiture.dto.ModelDTO;
import com.GestionLocationVoiture.entities.Model;

public class ModelMapper {
    
    public static ModelDTO toDTO(Model model) {
        ModelDTO dto = new ModelDTO();
        dto.setId(model.getId());
        dto.setNom(model.getNom());
        dto.setAnnee(model.getAnnee());
        if (model.getBrand() != null) {
            dto.setBrandId(model.getBrand().getId());
            
            // Map full brand for frontend display
            com.GestionLocationVoiture.dto.BrandDTO brandDTO = new com.GestionLocationVoiture.dto.BrandDTO();
            brandDTO.setId(model.getBrand().getId());
            brandDTO.setNom(model.getBrand().getNom());
            brandDTO.setPays(model.getBrand().getPays());
            dto.setBrand(brandDTO);
        }
        return dto;
    }
    
    public static Model toEntity(ModelDTO dto) {
        Model model = new Model();
        model.setId(dto.getId());
        model.setNom(dto.getNom());
        model.setAnnee(dto.getAnnee());
        return model;
    }
}
