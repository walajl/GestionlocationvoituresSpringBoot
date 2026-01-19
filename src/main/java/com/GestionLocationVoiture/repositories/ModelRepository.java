package com.GestionLocationVoiture.repositories;

import com.GestionLocationVoiture.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    
    List<Model> findByBrandId(Long brandId);
    
    List<Model> findByNom(String nom);
}
