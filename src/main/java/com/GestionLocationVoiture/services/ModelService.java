package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.ModelDTO;
import java.util.List;

public interface ModelService {
    ModelDTO saveModel(ModelDTO dto);
    ModelDTO updateModel(ModelDTO dto);
    void deleteModel(ModelDTO dto);
    void deleteModelById(Long id);
    ModelDTO getModel(Long id);
    List<ModelDTO> getAllModels();
    List<ModelDTO> getModelsByBrandId(Long brandId);
}
