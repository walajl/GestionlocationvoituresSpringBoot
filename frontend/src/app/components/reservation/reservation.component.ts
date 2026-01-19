import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReservationService } from '../../services/reservation.service';
import { CarService } from '../../services/car.service';
import { AuthService } from '../../services/auth.service';
import { NavigationService } from '../../services/navigation.service';
import { Car } from '../../models/car';

@Component({
    selector: 'app-reservation',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './reservation.component.html',
    styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
    authService = inject(AuthService);
    carService = inject(CarService);
    reservationService = inject(ReservationService);
    navService = inject(NavigationService);

    reservation = {
        dateDebut: '',
        dateFin: '',
        userId: 0,
        carId: 0
    };

    cars: Car[] = [];
    message: string = '';

    ngOnInit() {
        this.loadCars();
        const user = this.authService.currentUser();
        if (user) {
            this.reservation.userId = user.id;
        }

        const preSelectedCarId = this.navService.selectedCarId$.value;
        if (preSelectedCarId) {
            this.reservation.carId = preSelectedCarId;
            this.navService.selectedCarId$.next(null);
        }
    }

    loadCars() {
        this.carService.getCars().subscribe(data => {
            this.cars = data.filter(c => c.statut === 'DISPONIBLE');
        });
    }

    onSubmit() {
        this.reservationService.createReservation(this.reservation as any).subscribe({
            next: (data) => {
                this.message = '✅ Réservation créée avec succès ! Elle est en attente de validation.';
                this.reservation = {
                    dateDebut: '',
                    dateFin: '',
                    userId: this.authService.currentUser()?.id || 0,
                    carId: 0
                };
            },
            error: (e) => {
                this.message = '❌ Erreur lors de la réservation.';
                console.error(e);
            }
        });
    }
}
