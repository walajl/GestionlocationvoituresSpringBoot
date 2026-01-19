package com.GestionLocationVoiture.entities;

import javax.persistence.*;


@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String immatriculation;
    private String couleur;
    private Integer kilometrage;
    private Double prixParJour;
    private String statut; // DISPONIBLE, LOUE, EN_MAINTENANCE
    private String imageUrl; // URL de l'image du véhicule
    
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    
    public Car() {
        super();
    }
    
    public Car(String immatriculation, String couleur, Integer kilometrage, Double prixParJour, String statut) {
        super();
        this.immatriculation = immatriculation;
        this.couleur = couleur;
        this.kilometrage = kilometrage;
        this.prixParJour = prixParJour;
        this.statut = statut;
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

    public Integer getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Integer kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Double getPrixParJour() {
        return prixParJour;
    }

    public void setPrixParJour(Double prixParJour) {
        this.prixParJour = prixParJour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", immatriculation=" + immatriculation + ", couleur=" + couleur
                + ", kilometrage=" + kilometrage + ", prixParJour=" + prixParJour + ", statut=" + statut + "]";
    }
}
