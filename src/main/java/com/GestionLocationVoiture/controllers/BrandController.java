package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.BrandDTO;
import com.GestionLocationVoiture.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    
    @Autowired
    private BrandService brandService;
    
    @GetMapping
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }
    
    @GetMapping("/{id}")
    public BrandDTO getBrandById(@PathVariable Long id) {
        return brandService.getBrand(id);
    }
    
    @PostMapping
    public BrandDTO createBrand(@RequestBody BrandDTO dto) {
        return brandService.saveBrand(dto);
    }
    
    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable Long id, @RequestBody BrandDTO dto) {
        dto.setId(id);
        return brandService.updateBrand(dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrandById(id);
    }
}
