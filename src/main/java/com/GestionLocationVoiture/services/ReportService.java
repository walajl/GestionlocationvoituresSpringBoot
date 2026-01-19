package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.CarDTO;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<CarDTO> getVoituresReservees(LocalDate dateDebut, LocalDate dateFin);
    List<CarDTO> getVoituresLouees(LocalDate dateDebut, LocalDate dateFin);
    Double getRevenuTotal();
}
