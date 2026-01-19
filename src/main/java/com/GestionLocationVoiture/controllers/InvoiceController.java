package com.GestionLocationVoiture.controllers;

import com.GestionLocationVoiture.dto.InvoiceDTO;
import com.GestionLocationVoiture.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    
    @Autowired
    private InvoiceService invoiceService;
    
    @GetMapping
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
    
    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoice(id);
    }
    
    @PostMapping
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO dto) {
        return invoiceService.saveInvoice(dto);
    }
    
    @PostMapping("/generate/{reservationId}")
    public InvoiceDTO generateInvoiceForReservation(@PathVariable Long reservationId) {
        return invoiceService.genererFacturePourReservation(reservationId);
    }
    
    @PutMapping("/{id}")
    public InvoiceDTO updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO dto) {
        dto.setId(id);
        return invoiceService.updateInvoice(dto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoiceById(id);
    }
}
