package com.GestionLocationVoiture.mapper;

import com.GestionLocationVoiture.dto.ReservationDTO;
import com.GestionLocationVoiture.entities.Reservation;

public class ReservationMapper {
    
    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDateDebut(reservation.getDateDebut());
        dto.setDateFin(reservation.getDateFin());
        dto.setStatut(reservation.getStatut());
        dto.setMontantTotal(reservation.getMontantTotal());
        dto.setDateCreation(reservation.getDateCreation());
        if (reservation.getUser() != null) {
            dto.setUserId(reservation.getUser().getId());
            dto.setUser(UserMapper.toDTO(reservation.getUser()));
        }
        if (reservation.getCar() != null) {
            dto.setCarId(reservation.getCar().getId());
            dto.setCar(CarMapper.toDTO(reservation.getCar()));
        }
        return dto;
    }
    
    public static Reservation toEntity(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setId(dto.getId());
        reservation.setDateDebut(dto.getDateDebut());
        reservation.setDateFin(dto.getDateFin());
        reservation.setStatut(dto.getStatut());
        reservation.setMontantTotal(dto.getMontantTotal());
        reservation.setDateCreation(dto.getDateCreation());
        return reservation;
    }
}
