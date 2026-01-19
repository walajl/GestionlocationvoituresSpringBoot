package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.ModelDTO;
import com.GestionLocationVoiture.entities.Brand;
import com.GestionLocationVoiture.entities.Model;
import com.GestionLocationVoiture.mapper.ModelMapper;
import com.GestionLocationVoiture.repositories.BrandRepository;
import com.GestionLocationVoiture.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    
    @Autowired
    private ModelRepository modelRepository;
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Override
    public ModelDTO saveModel(ModelDTO dto) {
        Model model = ModelMapper.toEntity(dto);
        Long brandId = dto.getBrandId();
        if (brandId == null && dto.getBrand() != null) {
            brandId = dto.getBrand().getId();
        }
        
        if (brandId != null) {
            Brand brand = brandRepository.findById(brandId).get();
            model.setBrand(brand);
        }
        model = modelRepository.save(model);
        return ModelMapper.toDTO(model);
    }
    
    @Override
    public ModelDTO updateModel(ModelDTO dto) {
        Model model = ModelMapper.toEntity(dto);
        Long brandId = dto.getBrandId();
        if (brandId == null && dto.getBrand() != null) {
            brandId = dto.getBrand().getId();
        }
        
        if (brandId != null) {
            Brand brand = brandRepository.findById(brandId).get();
            model.setBrand(brand);
        }
        model = modelRepository.save(model);
        return ModelMapper.toDTO(model);
    }
    
    @Override
    public void deleteModel(ModelDTO dto) {
        Model model = ModelMapper.toEntity(dto);
        modelRepository.delete(model);
    }
    
    @Override
    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }
    
    @Override
    public ModelDTO getModel(Long id) {
        Model model = modelRepository.findById(id).get();
        return ModelMapper.toDTO(model);
    }
    
    @Override
    public List<ModelDTO> getAllModels() {
        return modelRepository.findAll()
                .stream()
                .map(ModelMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ModelDTO> getModelsByBrandId(Long brandId) {
        return modelRepository.findByBrandId(brandId)
                .stream()
                .map(ModelMapper::toDTO)
                .collect(Collectors.toList());
    }
}
