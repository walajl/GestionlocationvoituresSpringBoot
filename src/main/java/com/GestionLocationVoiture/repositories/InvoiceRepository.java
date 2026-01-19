package com.GestionLocationVoiture.repositories;

import com.GestionLocationVoiture.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    Invoice findByReservationId(Long reservationId);
    
    Invoice findByNumeroFacture(String numeroFacture);
    
    boolean existsByNumeroFacture(String numeroFacture);
}
