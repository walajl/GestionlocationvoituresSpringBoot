package com.GestionLocationVoiture.services;

import com.GestionLocationVoiture.dto.ReservationDTO;

import com.GestionLocationVoiture.entities.Car;
import com.GestionLocationVoiture.entities.Reservation;
import com.GestionLocationVoiture.entities.User;
import com.GestionLocationVoiture.mapper.ReservationMapper;
import com.GestionLocationVoiture.repositories.CarRepository;
import com.GestionLocationVoiture.repositories.ReservationRepository;
import com.GestionLocationVoiture.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CarRepository carRepository;
    
    @Override
    public ReservationDTO saveReservation(ReservationDTO dto) {
        Reservation reservation = ReservationMapper.toEntity(dto);
        
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).get();
            reservation.setUser(user);
        }
        
        if (dto.getCarId() != null) {
            Car car = carRepository.findById(dto.getCarId()).get();
            reservation.setCar(car);
            
            long jours = ChronoUnit.DAYS.between(dto.getDateDebut(), dto.getDateFin());
            double montant = car.getPrixParJour() * jours;
            reservation.setMontantTotal(montant);
        }
        
        reservation.setDateCreation(LocalDateTime.now());
        reservation.setStatut("EN_ATTENTE");
        
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(reservation);
    }
    
    @Override
    public ReservationDTO updateReservation(ReservationDTO dto) {
        Reservation reservation = ReservationMapper.toEntity(dto);
        
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId()).get();
            reservation.setUser(user);
        }
        
        if (dto.getCarId() != null) {
            Car car = carRepository.findById(dto.getCarId()).get();
            reservation.setCar(car);
        }
        
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(reservation);
    }
    
    @Override
    public void deleteReservation(ReservationDTO dto) {
        Reservation reservation = ReservationMapper.toEntity(dto);
        reservationRepository.delete(reservation);
    }
    
    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
    
    @Override
    public ReservationDTO getReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        return ReservationMapper.toDTO(reservation);
    }
    
    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "id"))
                .stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public ReservationDTO accepterReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStatut("ACCEPTEE");
        
        Car car = reservation.getCar();
        car.setStatut("LOUE");
        carRepository.save(car);
        
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(reservation);
    }
    
    @Override
    public ReservationDTO annulerReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setStatut("ANNULEE");
        
        Car car = reservation.getCar();
        car.setStatut("DISPONIBLE");
        carRepository.save(car);
        
        reservation = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(reservation);
    }
    
    @Override
    public List<ReservationDTO> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
