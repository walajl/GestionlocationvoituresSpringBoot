package com.GestionLocationVoiture.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Brand {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String pays;
    
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Model> models;
    
    public Brand() {
        super();
    }
    
    public Brand(String nom, String pays) {
        super();
        this.nom = nom;
        this.pays = pays;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand [id=" + id + ", nom=" + nom + ", pays=" + pays + "]";
    }
}
