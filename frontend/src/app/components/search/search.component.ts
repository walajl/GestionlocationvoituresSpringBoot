import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { Car } from '../../models/car';

@Component({
    selector: 'app-search',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent {
    // Critères de recherche
    brand: string = '';
    model: string = '';

    // Résultats de la recherche
    results: Car[] = [];
    hasSearched: boolean = false;

    constructor(private carService: CarService) { }

    // Fonction appelée lors du clic sur "Rechercher"
    onSearch() {
        this.hasSearched = true;
        // Appel au service (qui appelle l'API Spring Boot)
        this.carService.searchCars(this.brand, this.model).subscribe({
            next: (data) => {
                this.results = data;
                console.log('Résultats trouvés:', data.length);
            },
            error: (e) => console.error('Erreur recherche', e)
        });
    }
}
