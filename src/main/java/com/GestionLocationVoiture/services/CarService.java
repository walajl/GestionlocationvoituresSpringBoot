package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.CarDTO;
import java.util.List;

public interface CarService {
    CarDTO saveVoiture(CarDTO dto);
    CarDTO updateVoiture(CarDTO dto);
    void deleteVoiture(CarDTO dto);
    void deleteVoitureById(Long id);
    CarDTO getVoiture(Long id);
    List<CarDTO> getAllVoitures();
}

