package com.GestionLocationVoiture.mapper;

import com.GestionLocationVoiture.dto.BrandDTO;
import com.GestionLocationVoiture.dto.CarDTO;
import com.GestionLocationVoiture.dto.ModelDTO;
import com.GestionLocationVoiture.entities.Car;

public class CarMapper {
    
    public static CarDTO toDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setImmatriculation(car.getImmatriculation());
        dto.setCouleur(car.getCouleur());
        if (car.getKilometrage() != null) {
            dto.setKilometrage(car.getKilometrage());
        }
        if (car.getPrixParJour() != null) {
            dto.setPrixParJour(car.getPrixParJour());
        }
        dto.setStatut(car.getStatut());
        dto.setImageUrl(car.getImageUrl());
        
        // Map nested Model with Brand for frontend display
        if (car.getModel() != null) {
            dto.setModelId(car.getModel().getId());
            
            ModelDTO modelDTO = new ModelDTO();
            modelDTO.setId(car.getModel().getId());
            modelDTO.setNom(car.getModel().getNom());
            modelDTO.setAnnee(car.getModel().getAnnee());
            
            if (car.getModel().getBrand() != null) {
                modelDTO.setBrandId(car.getModel().getBrand().getId());
                
                BrandDTO brandDTO = new BrandDTO();
                brandDTO.setId(car.getModel().getBrand().getId());
                brandDTO.setNom(car.getModel().getBrand().getNom());
                modelDTO.setBrand(brandDTO);
            }
            
            dto.setModel(modelDTO);
        }
        
        return dto;
    }
    
    public static Car toEntity(CarDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setImmatriculation(dto.getImmatriculation());
        car.setCouleur(dto.getCouleur());
        car.setKilometrage(dto.getKilometrage());
        car.setPrixParJour(dto.getPrixParJour());
        car.setStatut(dto.getStatut());
        car.setImageUrl(dto.getImageUrl());
        return car;
    }
}
