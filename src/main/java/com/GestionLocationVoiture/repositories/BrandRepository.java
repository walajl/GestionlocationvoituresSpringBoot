package com.GestionLocationVoiture.repositories;

import com.GestionLocationVoiture.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    
    Brand findByNom(String nom);
    
    boolean existsByNom(String nom);
}
