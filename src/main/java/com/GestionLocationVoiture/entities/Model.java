package com.GestionLocationVoiture.entities;

import javax.persistence.*;


@Entity
public class Model {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private Integer annee;
    
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    
    public Model() {
        super();
    }
    
    public Model(String nom, Integer annee) {
        super();
        this.nom = nom;
        this.annee = annee;
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

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Model [id=" + id + ", nom=" + nom + ", annee=" + annee + "]";
    }
}
