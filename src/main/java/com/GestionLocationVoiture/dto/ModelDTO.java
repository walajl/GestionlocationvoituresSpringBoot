package com.GestionLocationVoiture.dto;

public class ModelDTO {
    
    private Long id;
    private String nom;
    private int annee;
    private Long brandId;
    
    // Nested brand for display
    private BrandDTO brand;
    
    public ModelDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getAnnee() {
        return annee;
    }
    
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
    public Long getBrandId() {
        return brandId;
    }
    
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
    
    public BrandDTO getBrand() {
        return brand;
    }
    
    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }
}
