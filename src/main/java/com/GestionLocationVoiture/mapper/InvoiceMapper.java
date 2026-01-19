package com.GestionLocationVoiture.mapper;

import com.GestionLocationVoiture.dto.InvoiceDTO;
import com.GestionLocationVoiture.entities.Invoice;

public class InvoiceMapper {
    
    public static InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setNumeroFacture(invoice.getNumeroFacture());
        dto.setDateFacture(invoice.getDateFacture());
        dto.setMontantHT(invoice.getMontantHT());
        dto.setTva(invoice.getTva());
        dto.setMontantTTC(invoice.getMontantTTC());
        if (invoice.getReservation() != null) {
            dto.setReservationId(invoice.getReservation().getId());
        }
        return dto;
    }
    
    public static Invoice toEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getId());
        invoice.setNumeroFacture(dto.getNumeroFacture());
        invoice.setDateFacture(dto.getDateFacture());
        invoice.setMontantHT(dto.getMontantHT());
        invoice.setTva(dto.getTva());
        invoice.setMontantTTC(dto.getMontantTTC());
        return invoice;
    }
}
