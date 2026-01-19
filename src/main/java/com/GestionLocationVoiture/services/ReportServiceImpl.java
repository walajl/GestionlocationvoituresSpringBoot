package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.CarDTO;
import com.GestionLocationVoiture.mapper.CarMapper;
import com.GestionLocationVoiture.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Override
    public List<CarDTO> getVoituresReservees(LocalDate dateDebut, LocalDate dateFin) {
        return reservationRepository.findReservationsByPeriod(dateDebut, dateFin)
                .stream()
                .map(reservation -> CarMapper.toDTO(reservation.getCar()))
                .distinct()
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CarDTO> getVoituresLouees(LocalDate dateDebut, LocalDate dateFin) {
        return reservationRepository.findActiveRentalsByPeriod(dateDebut, dateFin)
                .stream()
                .map(reservation -> CarMapper.toDTO(reservation.getCar()))
                .distinct()
                .collect(Collectors.toList());
    }
    
    @Override
    public Double getRevenuTotal() {
        return reservationRepository.calculateTotalRevenue();
    }
}
