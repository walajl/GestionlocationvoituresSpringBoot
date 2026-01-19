package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.CarDTO;
import com.GestionLocationVoiture.entities.Car;
import com.GestionLocationVoiture.entities.Model;
import com.GestionLocationVoiture.mapper.CarMapper;
import com.GestionLocationVoiture.repositories.CarRepository;
import com.GestionLocationVoiture.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private ModelRepository modelRepository;
    
    @Override
    public CarDTO saveVoiture(CarDTO dto) {
        Car car = CarMapper.toEntity(dto);
        if (dto.getModelId() != null) {
            Model model = modelRepository.findById(dto.getModelId()).get();
            car.setModel(model);
        }
        car = carRepository.save(car);
        return CarMapper.toDTO(car);
    }
    
    @Override
    public CarDTO updateVoiture(CarDTO dto) {
        Car car = CarMapper.toEntity(dto);
        if (dto.getModelId() != null) {
            Model model = modelRepository.findById(dto.getModelId()).get();
            car.setModel(model);
        }
        car = carRepository.save(car);
        return CarMapper.toDTO(car);
    }
    
    @Override
    public void deleteVoiture(CarDTO dto) {
        Car car = CarMapper.toEntity(dto);
        carRepository.delete(car);
    }
    
    @Override
    public void deleteVoitureById(Long id) {
        carRepository.deleteById(id);
    }
    
    @Override
    public CarDTO getVoiture(Long id) {
        Car car = carRepository.findById(id).get();
        return CarMapper.toDTO(car);
    }
    
    @Override
    public List<CarDTO> getAllVoitures() {
        return carRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "id"))
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
    }
}

