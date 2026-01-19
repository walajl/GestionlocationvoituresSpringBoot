import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../models/reservation';

@Injectable({
    providedIn: 'root'
})
export class ReservationService {
    private apiUrl = 'http://localhost:8081/reservations';

    constructor(private http: HttpClient) { }

    createReservation(reservation: Reservation): Observable<Reservation> {
        return this.http.post<Reservation>(this.apiUrl, reservation);
    }

    // Méthode pour l'Admin : Voir toutes les réservations
    getReservations(): Observable<Reservation[]> {
        return this.http.get<Reservation[]>(this.apiUrl);
    }

    // Méthode pour le Client : Voir ses réservations
    getReservationsByUser(userId: number): Observable<Reservation[]> {
        return this.http.get<Reservation[]>(`${this.apiUrl}/user/${userId}`);
    }

    // Méthode pour l'Admin : Valider ou Annuler
    updateStatus(id: number, action: 'accept' | 'cancel'): Observable<any> {
        const endpoint = action === 'accept' ? 'accepter' : 'annuler';
        return this.http.put(`${this.apiUrl}/${id}/${endpoint}`, {});
    }
}
