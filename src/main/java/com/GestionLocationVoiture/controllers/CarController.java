package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.CarDTO;
import com.GestionLocationVoiture.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    
    @Autowired
    private CarService carService;
    
    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllVoitures();
    }
    
    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable Long id) {
        return carService.getVoiture(id);
    }

    
    @PostMapping
    public CarDTO createCar(@RequestBody CarDTO dto) {
        return carService.saveVoiture(dto);
    }
    
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO dto) {
        dto.setId(id);
        return carService.updateVoiture(dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteVoitureById(id);
    }
}
