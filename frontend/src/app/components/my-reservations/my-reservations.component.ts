import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationService } from '../../services/reservation.service';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-my-reservations',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './my-reservations.component.html',
    styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {
    reservationService = inject(ReservationService);
    authService = inject(AuthService);

    reservations = signal<any[]>([]);
    loading = signal<boolean>(false);

    // Chargement initial des réservations de l'utilisateur
    ngOnInit() {
        this.loadReservations();
    }

    // Récupère les réservations de l'utilisateur connecté
    loadReservations() {
        const user = this.authService.currentUser();
        if (user) {
            this.loading.set(true);
            this.reservationService.getReservationsByUser(user.id).subscribe({
                next: (data) => {
                    this.reservations.set(data);
                    this.loading.set(false);
                },
                error: (e) => {
                    console.error(e);
                    this.loading.set(false);
                }
            });
        }
    }

    // --- Méthodes de Statistiques ---

    // Calcule le montant total dépensé (réservations confirmées)
    getTotalSpent(): number {
        return this.reservations()
            .filter(r => r.statut === 'ACCEPTEE')
            .reduce((sum, r) => sum + (r.montantTotal || 0), 0);
    }

    // Compte les réservations en attente
    getPendingCount(): number {
        return this.reservations().filter(r => r.statut === 'EN_ATTENTE').length;
    }

    // Compte les réservations confirmées
    getAcceptedCount(): number {
        return this.reservations().filter(r => r.statut === 'ACCEPTEE').length;
    }

    // --- Méthodes d'Affichage ---

    // Retourne la couleur du texte selon le statut
    getStatusColor(status: string): string {
        switch (status) {
            case 'EN_ATTENTE': return '#f59e0b';
            case 'ACCEPTEE': return '#22c55e';
            case 'ANNULEE': return '#ef4444';
            default: return '#64748b';
        }
    }

    // Retourne la couleur de fond selon le statut
    getStatusBg(status: string): string {
        switch (status) {
            case 'EN_ATTENTE': return '#fef3c7';
            case 'ACCEPTEE': return '#dcfce7';
            case 'ANNULEE': return '#fee2e2';
            default: return '#f1f5f9';
        }
    }

    // Retourne le libellé lisible du statut
    getStatusLabel(status: string): string {
        switch (status) {
            case 'EN_ATTENTE': return 'En attente';
            case 'ACCEPTEE': return 'Confirmée';
            case 'ANNULEE': return 'Annulée';
            default: return status;
        }
    }

    // Annule une réservation (pour le client)
    cancelReservation(id: number) {
        if (confirm('Voulez-vous vraiment annuler cette réservation ?')) {
            this.reservationService.updateStatus(id, 'cancel').subscribe({
                next: () => {
                    this.loadReservations();
                },
                error: (e) => console.error(e)
            });
        }
    }
}
