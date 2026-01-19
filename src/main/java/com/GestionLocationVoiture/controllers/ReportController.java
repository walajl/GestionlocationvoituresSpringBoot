package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.CarDTO;
import com.GestionLocationVoiture.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @GetMapping("/reserved-cars/{dateDebut}/{dateFin}")
    public List<CarDTO> getReservedCars(
            @PathVariable String dateDebut, 
            @PathVariable String dateFin) {
        return reportService.getVoituresReservees(
                LocalDate.parse(dateDebut), 
                LocalDate.parse(dateFin));
    }
    
    @GetMapping("/rented-cars/{dateDebut}/{dateFin}")
    public List<CarDTO> getRentedCars(
            @PathVariable String dateDebut, 
            @PathVariable String dateFin) {
        return reportService.getVoituresLouees(
                LocalDate.parse(dateDebut), 
                LocalDate.parse(dateFin));
    }
    
    @GetMapping("/total-revenue")
    public Double getTotalRevenue() {
        return reportService.getRevenuTotal();
    }
}
