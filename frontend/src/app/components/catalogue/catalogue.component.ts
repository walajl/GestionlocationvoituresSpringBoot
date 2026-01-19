import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { ReservationService } from '../../services/reservation.service';
import { AuthService } from '../../services/auth.service';
import { Car } from '../../models/car';
import { NavigationService } from '../../services/navigation.service';

@Component({
    selector: 'app-catalogue',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './catalogue.component.html',
    styleUrls: ['./catalogue.component.css']
})
export class CatalogueComponent implements OnInit {
    carService = inject(CarService);
    reservationService = inject(ReservationService);
    authService = inject(AuthService);
    navService = inject(NavigationService);

    cars = signal<Car[]>([]);
    selectedCar = signal<Car | null>(null);

    dateDebut = '';
    dateFin = '';

    showModal = signal<boolean>(false);
    message = signal<string | null>(null);
    loading = signal<boolean>(false);

    ngOnInit() {
        this.loadCars();
    }

    loadCars() {
        this.carService.getCars().subscribe({
            next: (data) => this.cars.set(data.filter(c => c.statut === 'DISPONIBLE')),
            error: (e) => console.error(e)
        });
    }

    openReservationModal(car: Car) {
        this.selectedCar.set(car);
        this.showModal.set(true);
        this.dateDebut = '';
        this.dateFin = '';
        this.message.set(null);
    }

    closeModal() {
        this.showModal.set(false);
        this.selectedCar.set(null);
    }

    submitReservation() {
        const user = this.authService.currentUser();
        const car = this.selectedCar();

        if (!user || !car) return;

        this.loading.set(true);

        const reservation = {
            dateDebut: this.dateDebut,
            dateFin: this.dateFin,
            userId: user.id,
            carId: car.id
        };

        this.reservationService.createReservation(reservation).subscribe({
            next: () => {
                this.loading.set(false);
                this.message.set('Réservation envoyée ! Redirection...');
                setTimeout(() => {
                    this.closeModal();
                    this.navService.switchTab('my-reservations');
                }, 1500);
            },
            error: () => {
                this.loading.set(false);
                this.message.set('Erreur lors de la réservation');
            }
        });
    }
}
