package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.ReservationDTO;
import com.GestionLocationVoiture.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }
    
    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }
    
    @GetMapping("/user/{userId}")
    public List<ReservationDTO> getReservationsByUser(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }
    
    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO dto) {
        return reservationService.saveReservation(dto);
    }
    
    @PutMapping("/{id}")
    public ReservationDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO dto) {
        dto.setId(id);
        return reservationService.updateReservation(dto);
    }
    
    @PutMapping("/{id}/accepter")
    public ReservationDTO acceptReservation(@PathVariable Long id) {
        return reservationService.accepterReservation(id);
    }
    
    @PutMapping("/{id}/annuler")
    public ReservationDTO cancelReservation(@PathVariable Long id) {
        return reservationService.annulerReservation(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
    }
}
