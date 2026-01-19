package com.GestionLocationVoiture.dto;

public class CarDTO {
    
    private Long id;
    private String immatriculation;
    private String couleur;
    private int kilometrage;
    private double prixParJour;
    private String statut;
    private String imageUrl;
    private Long modelId;
    
    // Nested model for display (includes brand)
    private ModelDTO model;
    
    public CarDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getImmatriculation() {
        return immatriculation;
    }
    
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
    
    public String getCouleur() {
        return couleur;
    }
    
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    public int getKilometrage() {
        return kilometrage;
    }
    
    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }
    
    public double getPrixParJour() {
        return prixParJour;
    }
    
    public void setPrixParJour(double prixParJour) {
        this.prixParJour = prixParJour;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Long getModelId() {
        return modelId;
    }
    
    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public ModelDTO getModel() {
        return model;
    }
    
    public void setModel(ModelDTO model) {
        this.model = model;
    }
}
