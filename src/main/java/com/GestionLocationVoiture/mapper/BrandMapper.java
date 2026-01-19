package com.GestionLocationVoiture.mapper;

import com.GestionLocationVoiture.dto.BrandDTO;
import com.GestionLocationVoiture.entities.Brand;

public class BrandMapper {
    
    public static BrandDTO toDTO(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setNom(brand.getNom());
        dto.setPays(brand.getPays());
        return dto;
    }
    
    public static Brand toEntity(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setId(dto.getId());
        brand.setNom(dto.getNom());
        brand.setPays(dto.getPays());
        return brand;
    }
}
