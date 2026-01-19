package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.InvoiceDTO;
import com.GestionLocationVoiture.entities.Invoice;
import com.GestionLocationVoiture.entities.Reservation;
import com.GestionLocationVoiture.mapper.InvoiceMapper;
import com.GestionLocationVoiture.repositories.InvoiceRepository;
import com.GestionLocationVoiture.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Override
    public InvoiceDTO saveInvoice(InvoiceDTO dto) {
        Invoice invoice = InvoiceMapper.toEntity(dto);
        
        if (dto.getReservationId() != null) {
            Reservation reservation = reservationRepository.findById(dto.getReservationId()).get();
            invoice.setReservation(reservation);
        }
        
        invoice = invoiceRepository.save(invoice);
        return InvoiceMapper.toDTO(invoice);
    }
    
    @Override
    public InvoiceDTO updateInvoice(InvoiceDTO dto) {
        Invoice invoice = InvoiceMapper.toEntity(dto);
        
        if (dto.getReservationId() != null) {
            Reservation reservation = reservationRepository.findById(dto.getReservationId()).get();
            invoice.setReservation(reservation);
        }
        
        invoice = invoiceRepository.save(invoice);
        return InvoiceMapper.toDTO(invoice);
    }
    
    @Override
    public void deleteInvoice(InvoiceDTO dto) {
        Invoice invoice = InvoiceMapper.toEntity(dto);
        invoiceRepository.delete(invoice);
    }
    
    @Override
    public void deleteInvoiceById(Long id) {
        invoiceRepository.deleteById(id);
    }
    
    @Override
    public InvoiceDTO getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).get();
        return InvoiceMapper.toDTO(invoice);
    }
    
    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(InvoiceMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public InvoiceDTO genererFacturePourReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        
        Invoice invoice = new Invoice();
        invoice.setReservation(reservation);
        invoice.setNumeroFacture("FACT-" + System.currentTimeMillis());
        invoice.setDateFacture(LocalDate.now());
        
        double montantTTC = reservation.getMontantTotal();
        double montantHT = montantTTC / 1.19;
        double tva = montantTTC - montantHT;
        
        invoice.setMontantTTC(montantTTC);
        invoice.setMontantHT(montantHT);
        invoice.setTva(tva);
        
        invoice = invoiceRepository.save(invoice);
        return InvoiceMapper.toDTO(invoice);
    }
}
