package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.BrandDTO;
import java.util.List;

public interface BrandService {
    BrandDTO saveBrand(BrandDTO dto);
    BrandDTO updateBrand(BrandDTO dto);
    void deleteBrand(BrandDTO dto);
    void deleteBrandById(Long id);
    BrandDTO getBrand(Long id);
    List<BrandDTO> getAllBrands();
}
