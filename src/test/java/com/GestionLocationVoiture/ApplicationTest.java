package com.GestionLocationVoiture;

import com.GestionLocationVoiture.dto.*;
import com.GestionLocationVoiture.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ApplicationTest {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BrandService brandService;
    
    @Autowired
    private ModelService modelService;
    
    @Autowired
    private CarService carService;
    
    @Autowired
    private ReservationService reservationService;
    
    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private ReportService reportService;
    
    @Test
    public void testToutesLesFonctionnalites() {
        // Créer 4 utilisateurs tunisiens
        UserDTO admin = new UserDTO();
        admin.setNom("Administrateur");
        admin.setPrenom("System");
        admin.setEmail("admin@rapidloc.tn");
        admin.setPassword("admin123");
        admin.setTelephone("20234567");
        admin.setAdresse("Avenue Habib Bourguiba, Tunis 1000");
        admin.setRole("ROLE_ADMIN");
        admin = userService.saveUtilisateur(admin);
        
        UserDTO client1 = new UserDTO();
        client1.setNom("Ben Salah");
        client1.setPrenom("Ahmed");
        client1.setEmail("ahmed.bensalah@gmail.com");
        client1.setPassword("password123");
        client1.setTelephone("98765432");
        client1.setAdresse("Rue de la Liberté, La Marsa, Tunis 2078");
        client1.setRole("ROLE_CLIENT");
        client1 = userService.saveUtilisateur(client1);
        
        UserDTO client2 = new UserDTO();
        client2.setNom("Trabelsi");
        client2.setPrenom("Fatma");
        client2.setEmail("fatma.trabelsi@outlook.com");
        client2.setPassword("password123");
        client2.setTelephone("25123456");
        client2.setAdresse("Avenue Hedi Chaker, Sfax 3000");
        client2.setRole("ROLE_CLIENT");
        client2 = userService.saveUtilisateur(client2);
        
        UserDTO client3 = new UserDTO();
        client3.setNom("Guesmi");
        client3.setPrenom("Mohamed");
        client3.setEmail("mohamed.guesmi@yahoo.fr");
        client3.setPassword("password123");
        client3.setTelephone("52987654");
        client3.setAdresse("Boulevard 14 Janvier, Sousse 4000");
        client3.setRole("ROLE_CLIENT");
        client3 = userService.saveUtilisateur(client3);
        
        userService.getAllUtilisateurs();
        
        // Créer 5 marques
        BrandDTO renault = new BrandDTO();
        renault.setNom("Renault");
        renault.setPays("France");
        renault = brandService.saveBrand(renault);
        
        BrandDTO peugeot = new BrandDTO();
        peugeot.setNom("Peugeot");
        peugeot.setPays("France");
        peugeot = brandService.saveBrand(peugeot);
        
        BrandDTO citroen = new BrandDTO();
        citroen.setNom("Citroen");
        citroen.setPays("France");
        citroen = brandService.saveBrand(citroen);
        
        BrandDTO toyota = new BrandDTO();
        toyota.setNom("Toyota");
        toyota.setPays("Japon");
        toyota = brandService.saveBrand(toyota);
        
        BrandDTO volkswagen = new BrandDTO();
        volkswagen.setNom("Volkswagen");
        volkswagen.setPays("Allemagne");
        volkswagen = brandService.saveBrand(volkswagen);
        
        brandService.getAllBrands();
        
        // Créer 7 modèles
        ModelDTO clio = new ModelDTO();
        clio.setNom("Clio V");
        clio.setAnnee(2023);
        clio.setBrandId(renault.getId());
        clio = modelService.saveModel(clio);
        
        ModelDTO megane = new ModelDTO();
        megane.setNom("Megane IV");
        megane.setAnnee(2024);
        megane.setBrandId(renault.getId());
        megane = modelService.saveModel(megane);
        
        ModelDTO p208 = new ModelDTO();
        p208.setNom("208 II");
        p208.setAnnee(2023);
        p208.setBrandId(peugeot.getId());
        p208 = modelService.saveModel(p208);
        
        ModelDTO p308 = new ModelDTO();
        p308.setNom("308 III");
        p308.setAnnee(2024);
        p308.setBrandId(peugeot.getId());
        p308 = modelService.saveModel(p308);
        
        ModelDTO c3 = new ModelDTO();
        c3.setNom("C3 III");
        c3.setAnnee(2022);
        c3.setBrandId(citroen.getId());
        c3 = modelService.saveModel(c3);
        
        ModelDTO corolla = new ModelDTO();
        corolla.setNom("Corolla");
        corolla.setAnnee(2024);
        corolla.setBrandId(toyota.getId());
        corolla = modelService.saveModel(corolla);
        
        ModelDTO golf = new ModelDTO();
        golf.setNom("Golf 8");
        golf.setAnnee(2023);
        golf.setBrandId(volkswagen.getId());
        golf = modelService.saveModel(golf);
        
        modelService.getAllModels();
        modelService.getModelsByBrandId(renault.getId());
        
        // Créer 7 voitures avec vraies immatriculations tunisiennes
        CarDTO car1 = new CarDTO();
        car1.setImmatriculation("200 TUN 2345");
        car1.setCouleur("Rouge Flamme");
        car1.setKilometrage(12500);
        car1.setPrixParJour(85.0);
        car1.setStatut("DISPONIBLE");
        car1.setModelId(clio.getId());
        car1 = carService.saveVoiture(car1);
        
        CarDTO car2 = new CarDTO();
        car2.setImmatriculation("256 TUN 6789");
        car2.setCouleur("Bleu Océan");
        car2.setKilometrage(8300);
        car2.setPrixParJour(95.0);
        car2.setStatut("DISPONIBLE");
        car2.setModelId(megane.getId());
        car2 = carService.saveVoiture(car2);
        
        CarDTO car3 = new CarDTO();
        car3.setImmatriculation("230 TUN 1234");
        car3.setCouleur("Blanc Nacré");
        car3.setKilometrage(15200);
        car3.setPrixParJour(78.0);
        car3.setStatut("DISPONIBLE");
        car3.setModelId(p208.getId());
        car3 = carService.saveVoiture(car3);
        
        CarDTO car4 = new CarDTO();
        car4.setImmatriculation("234 TUN 5678");
        car4.setCouleur("Noir Intense");
        car4.setKilometrage(9800);
        car4.setPrixParJour(105.0);
        car4.setStatut("DISPONIBLE");
        car4.setModelId(p308.getId());
        car4 = carService.saveVoiture(car4);
        
        CarDTO car5 = new CarDTO();
        car5.setImmatriculation("240 TUN 9012");
        car5.setCouleur("Gris Platine");
        car5.setKilometrage(18500);
        car5.setPrixParJour(72.0);
        car5.setStatut("DISPONIBLE");
        car5.setModelId(c3.getId());
        car5 = carService.saveVoiture(car5);
        
        CarDTO car6 = new CarDTO();
        car6.setImmatriculation("246 TUN 3456");
        car6.setCouleur("Argent Métallisé");
        car6.setKilometrage(6200);
        car6.setPrixParJour(115.0);
        car6.setStatut("DISPONIBLE");
        car6.setModelId(corolla.getId());
        car6 = carService.saveVoiture(car6);
        
        CarDTO car7 = new CarDTO();
        car7.setImmatriculation("239 TUN 7890");
        car7.setCouleur("Bleu Nuit");
        car7.setKilometrage(11400);
        car7.setPrixParJour(98.0);
        car7.setStatut("DISPONIBLE");
        car7.setModelId(golf.getId());
        car7 = carService.saveVoiture(car7);
        
        carService.getAllVoitures();
        
        // Créer 5 réservations réalistes
        ReservationDTO resa1 = new ReservationDTO();
        resa1.setDateDebut(LocalDate.of(2025, 12, 10));
        resa1.setDateFin(LocalDate.of(2025, 12, 15));
        resa1.setUserId(client1.getId());
        resa1.setCarId(car1.getId());
        resa1 = reservationService.saveReservation(resa1);
        
        ReservationDTO resa2 = new ReservationDTO();
        resa2.setDateDebut(LocalDate.of(2025, 12, 12));
        resa2.setDateFin(LocalDate.of(2025, 12, 18));
        resa2.setUserId(client2.getId());
        resa2.setCarId(car3.getId());
        resa2 = reservationService.saveReservation(resa2);
        
        ReservationDTO resa3 = new ReservationDTO();
        resa3.setDateDebut(LocalDate.of(2025, 12, 20));
        resa3.setDateFin(LocalDate.of(2025, 12, 28));
        resa3.setUserId(client3.getId());
        resa3.setCarId(car5.getId());
        resa3 = reservationService.saveReservation(resa3);
        
        ReservationDTO resa4 = new ReservationDTO();
        resa4.setDateDebut(LocalDate.of(2026, 1, 5));
        resa4.setDateFin(LocalDate.of(2026, 1, 12));
        resa4.setUserId(client1.getId());
        resa4.setCarId(car6.getId());
        resa4 = reservationService.saveReservation(resa4);
        
        ReservationDTO resa5 = new ReservationDTO();
        resa5.setDateDebut(LocalDate.of(2026, 1, 15));
        resa5.setDateFin(LocalDate.of(2026, 1, 22));
        resa5.setUserId(client2.getId());
        resa5.setCarId(car7.getId());
        resa5 = reservationService.saveReservation(resa5);
        
        reservationService.getAllReservations();
        reservationService.getReservationsByUserId(client1.getId());
        
        // Accepter 3 réservations et annuler 1
        resa1 = reservationService.accepterReservation(resa1.getId());
        resa2 = reservationService.accepterReservation(resa2.getId());
        resa3 = reservationService.accepterReservation(resa3.getId());
        reservationService.annulerReservation(resa5.getId());
        
        // Générer 3 factures
        InvoiceDTO facture1 = invoiceService.genererFacturePourReservation(resa1.getId());
        InvoiceDTO facture2 = invoiceService.genererFacturePourReservation(resa2.getId());
        InvoiceDTO facture3 = invoiceService.genererFacturePourReservation(resa3.getId());
        
        invoiceService.getAllInvoices();
        invoiceService.getInvoice(facture1.getId());
        
        // Tester rapports
        reportService.getVoituresReservees(LocalDate.of(2025, 12, 1), LocalDate.of(2026, 1, 31));
        reportService.getVoituresLouees(LocalDate.of(2025, 12, 1), LocalDate.of(2026, 1, 31));
        reportService.getRevenuTotal();
    }
}
