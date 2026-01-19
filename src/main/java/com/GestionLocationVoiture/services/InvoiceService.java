package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.InvoiceDTO;
import java.util.List;

public interface InvoiceService {
    InvoiceDTO saveInvoice(InvoiceDTO dto);
    InvoiceDTO updateInvoice(InvoiceDTO dto);
    void deleteInvoice(InvoiceDTO dto);
    void deleteInvoiceById(Long id);
    InvoiceDTO getInvoice(Long id);
    List<InvoiceDTO> getAllInvoices();
    InvoiceDTO genererFacturePourReservation(Long reservationId);
}
