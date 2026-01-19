package com.GestionLocationVoiture.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDTO {
    
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private Double montantTotal;
    private LocalDateTime dateCreation;
    private Long userId;
    private Long carId;
    
    // Nested DTOs for frontend display
    private UserDTO user;
    private CarDTO car;
    
    public ReservationDTO() {
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public LocalDate getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public void setStatut(String statut) {
        this.statut = statut;
    }
    
    public Double getMontantTotal() {
        return montantTotal;
    }
    
    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }
    
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getCarId() {
        return carId;
    }
    
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    
    public UserDTO getUser() {
        return user;
    }
    
    public void setUser(UserDTO user) {
        this.user = user;
    }
    
    public CarDTO getCar() {
        return car;
    }
    
    public void setCar(CarDTO car) {
        this.car = car;
    }
}
