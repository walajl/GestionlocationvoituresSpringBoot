import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ModelService, Model } from '../../services/model.service';
import { BrandService, Brand } from '../../services/brand.service';

@Component({
    selector: 'app-model',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './model.component.html',
    styleUrls: ['./model.component.css']
})
export class ModelComponent implements OnInit {
    models: Model[] = [];
    brands: Brand[] = [];
    currentModel: Model = { nom: '', annee: 2024, brand: { id: 0, nom: '', pays: '' } };
    isEditing: boolean = false;

    constructor(
        private modelService: ModelService,
        private brandService: BrandService
    ) { }

    ngOnInit(): void {
        this.loadModels();
        this.loadBrands();
    }

    loadModels() {
        this.modelService.getAll().subscribe(data => this.models = data);
    }

    loadBrands() {
        this.brandService.getAll().subscribe(data => this.brands = data);
    }

    onSubmit() {
        // Vérification basique
        if (!this.currentModel.nom) {
            alert('Veuillez saisir un nom de modèle');
            return;
        }
        if (!this.currentModel.annee) {
            alert('Veuillez saisir une année');
            return;
        }
        if (!this.currentModel.brand.id) {
            alert('Veuillez sélectionner une marque');
            return;
        }

        if (this.isEditing && this.currentModel.id) {
            this.modelService.update(this.currentModel.id, this.currentModel).subscribe(() => {
                this.resetForm();
                this.loadModels();
            });
        } else {
            this.modelService.create(this.currentModel).subscribe(() => {
                this.resetForm();
                this.loadModels();
            });
        }
    }

    editModel(model: Model) {
        this.currentModel = { ...model };
        // Hack pour s'assurer que la select box affiche la bonne marque (comparaison par référence vs valeur)
        // En production, on utiliserait [compareWith]
        this.isEditing = true;
    }

    deleteModel(id: number | undefined) {
        if (id && confirm('Supprimer ce modèle ?')) {
            this.modelService.delete(id).subscribe(() => this.loadModels());
        }
    }

    resetForm() {
        this.currentModel = { nom: '', annee: 2024, brand: { id: 0, nom: '', pays: '' } };
        this.isEditing = false;
    }
}
