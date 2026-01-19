package com.GestionLocationVoiture.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Invoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String numeroFacture;
    private LocalDate dateFacture;
    private Double montantHT;
    private Double tva;
    private Double montantTTC;
    
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    
    public Invoice() {
        super();
    }
    
    public Invoice(String numeroFacture, LocalDate dateFacture, Double montantHT, Double tva, Double montantTTC) {
        super();
        this.numeroFacture = numeroFacture;
        this.dateFacture = dateFacture;
        this.montantHT = montantHT;
        this.tva = tva;
        this.montantTTC = montantTTC;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(Double montantHT) {
        this.montantHT = montantHT;
    }

    public Double getTva() {
        return tva;
    }

    public void setTva(Double tva) {
        this.tva = tva;
    }

    public Double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(Double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", numeroFacture=" + numeroFacture + ", dateFacture=" + dateFacture
                + ", montantHT=" + montantHT + ", tva=" + tva + ", montantTTC=" + montantTTC + "]";
    }
}
