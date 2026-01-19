export interface Reservation {
    id?: number;
    dateDebut: string;
    dateFin: string;
    // For creating reservations
    userId?: number;
    carId?: number;
    // For displaying reservations (populated from backend)
    user?: { id: number; nom?: string; prenom?: string; email?: string };
    car?: { id: number; immatriculation?: string; model?: { nom?: string } };
    statut?: string;
    montantTotal?: number;
}
