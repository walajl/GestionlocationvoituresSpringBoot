package com.GestionLocationVoiture.repositories;

import com.GestionLocationVoiture.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findByUserId(Long userId);
    
    List<Reservation> findByCarId(Long carId);
    
    List<Reservation> findByStatut(String statut);
    
    @Query("SELECT r FROM Reservation r WHERE " +
           "(r.dateDebut BETWEEN :startDate AND :endDate) OR " +
           "(r.dateFin BETWEEN :startDate AND :endDate) OR " +
           "(r.dateDebut <= :startDate AND r.dateFin >= :endDate)")
    List<Reservation> findReservationsByPeriod(@Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
    
    @Query("SELECT r FROM Reservation r WHERE r.statut = 'ACCEPTEE' AND " +
           "((r.dateDebut BETWEEN :startDate AND :endDate) OR " +
           "(r.dateFin BETWEEN :startDate AND :endDate) OR " +
           "(r.dateDebut <= :startDate AND r.dateFin >= :endDate))")
    List<Reservation> findActiveRentalsByPeriod(@Param("startDate") LocalDate startDate,
                                                 @Param("endDate") LocalDate endDate);
    
    @Query("SELECT SUM(r.montantTotal) FROM Reservation r WHERE r.statut = 'ACCEPTEE'")
    Double calculateTotalRevenue();
}
