package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.BrandDTO;
import com.GestionLocationVoiture.entities.Brand;
import com.GestionLocationVoiture.mapper.BrandMapper;
import com.GestionLocationVoiture.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Override
    public BrandDTO saveBrand(BrandDTO dto) {
        Brand brand = BrandMapper.toEntity(dto);
        brand = brandRepository.save(brand);
        return BrandMapper.toDTO(brand);
    }
    
    @Override
    public BrandDTO updateBrand(BrandDTO dto) {
        Brand brand = BrandMapper.toEntity(dto);
        brand = brandRepository.save(brand);
        return BrandMapper.toDTO(brand);
    }
    
    @Override
    public void deleteBrand(BrandDTO dto) {
        Brand brand = BrandMapper.toEntity(dto);
        brandRepository.delete(brand);
    }
    
    @Override
    public void deleteBrandById(Long id) {
        brandRepository.deleteById(id);
    }
    
    @Override
    public BrandDTO getBrand(Long id) {
        Brand brand = brandRepository.findById(id).get();
        return BrandMapper.toDTO(brand);
    }
    
    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(BrandMapper::toDTO)
                .collect(Collectors.toList());
    }
}
