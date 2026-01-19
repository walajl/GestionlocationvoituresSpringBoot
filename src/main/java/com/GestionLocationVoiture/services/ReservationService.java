package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.ReservationDTO;
import java.util.List;


public interface ReservationService {
    ReservationDTO saveReservation(ReservationDTO dto);
    ReservationDTO updateReservation(ReservationDTO dto);
    void deleteReservation(ReservationDTO dto);
    void deleteReservationById(Long id);
    ReservationDTO getReservation(Long id);
    List<ReservationDTO> getAllReservations();
    ReservationDTO accepterReservation(Long id);
    ReservationDTO annulerReservation(Long id);
    List<ReservationDTO> getReservationsByUserId(Long userId);
}
