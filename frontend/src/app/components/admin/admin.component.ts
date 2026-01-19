import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReservationService } from '../../services/reservation.service';
import { Reservation } from '../../models/reservation';

@Component({
    selector: 'app-admin',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './admin.component.html',
    styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
    reservations: Reservation[] = [];
    loading: boolean = false;
    message: string | null = null;
    messageType: 'success' | 'error' = 'success';

    constructor(
        private reservationService: ReservationService,
        private cdr: ChangeDetectorRef
    ) { }

    // Chargement initial des réservations
    ngOnInit(): void {
        this.loadReservations();
    }

    // Récupère toutes les réservations
    loadReservations() {
        this.loading = true;
        this.reservationService.getReservations().subscribe({
            next: (data) => {
                this.reservations = data;
                this.loading = false;
                this.cdr.detectChanges();
            },
            error: (e) => {
                this.loading = false;
                this.showMessage('Erreur lors du chargement des réservations', 'error');
                console.error('Erreur chargement réservations', e);
            }
        });
    }

    // Met à jour le statut d'une réservation (Accepter ou Annuler)
    updateStatus(id: number | undefined, action: 'accept' | 'cancel') {
        if (!id) return;

        this.loading = true;
        this.reservationService.updateStatus(id, action).subscribe({
            next: () => {
                const actionText = action === 'accept' ? 'validée' : 'annulée';
                this.showMessage(`Réservation ${actionText} avec succès !`, 'success');
                this.loadReservations();
            },
            error: (e) => {
                this.loading = false;
                this.showMessage('Erreur lors de la mise à jour', 'error');
                console.error('Update error:', e);
            }
        });
    }

    // Affiche un message temporaire
    showMessage(msg: string, type: 'success' | 'error') {
        this.message = msg;
        this.messageType = type;
        setTimeout(() => this.message = null, 3000);
    }

    // --- Méthodes de Statistiques ---

    // Calcule le Chiffre d'Affaires total (réservations acceptées uniquement)
    getTotalCA(): number {
        return this.reservations
            .filter(r => r.statut === 'ACCEPTEE')
            .reduce((sum, r) => sum + (r.montantTotal || 0), 0);
    }

    // Compte les réservations en attente
    getPendingCount(): number {
        return this.reservations.filter(r => r.statut === 'EN_ATTENTE').length;
    }

    // Compte les réservations acceptées
    getAcceptedCount(): number {
        return this.reservations.filter(r => r.statut === 'ACCEPTEE').length;
    }
}
