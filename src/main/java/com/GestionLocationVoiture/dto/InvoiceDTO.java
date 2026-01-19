package com.GestionLocationVoiture.dto;

import java.time.LocalDate;

public class InvoiceDTO {
    
    private Long id;
    private String numeroFacture;
    private LocalDate dateFacture;
    private Double montantHT;
    private Double tva;
    private Double montantTTC;
    private Long reservationId;
    
    public InvoiceDTO() {
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
    
    public Long getReservationId() {
        return reservationId;
    }
    
    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
}
