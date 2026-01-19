package com.GestionLocationVoiture.repositories;

import com.GestionLocationVoiture.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    
    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.model m LEFT JOIN FETCH m.brand")
    List<Car> findAll();

    List<Car> findByStatut(String statut);
    
    List<Car> findByModelId(Long modelId);
    
    @Query("SELECT c FROM Car c WHERE c.model.brand.id = :brandId")
    List<Car> findByBrandId(@Param("brandId") Long brandId);
    
    @Query("SELECT c FROM Car c WHERE c.model.brand.id = :brandId AND c.model.id = :modelId")
    List<Car> findByBrandIdAndModelId(@Param("brandId") Long brandId, @Param("modelId") Long modelId);
    
    boolean existsByImmatriculation(String immatriculation);
}
