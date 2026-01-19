package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.ModelDTO;
import com.GestionLocationVoiture.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    
    @Autowired
    private ModelService modelService;
    
    @GetMapping
    public List<ModelDTO> getAllModels() {
        return modelService.getAllModels();
    }
    
    @GetMapping("/{id}")
    public ModelDTO getModelById(@PathVariable Long id) {
        return modelService.getModel(id);
    }
    
    @GetMapping("/brand/{brandId}")
    public List<ModelDTO> getModelsByBrand(@PathVariable Long brandId) {
        return modelService.getModelsByBrandId(brandId);
    }
    
    @PostMapping
    public ModelDTO createModel(@RequestBody ModelDTO dto) {
        return modelService.saveModel(dto);
    }
    
    @PutMapping("/{id}")
    public ModelDTO updateModel(@PathVariable Long id, @RequestBody ModelDTO dto) {
        dto.setId(id);
        return modelService.updateModel(dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelService.deleteModelById(id);
    }
}
