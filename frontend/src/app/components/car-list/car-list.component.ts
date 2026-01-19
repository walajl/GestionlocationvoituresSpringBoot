import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { FileService } from '../../services/file.service';
import { AuthService } from '../../services/auth.service';
import { NavigationService } from '../../services/navigation.service';
import { ModelService, Model } from '../../services/model.service';
import { Car } from '../../models/car';

@Component({
    selector: 'app-car-list',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './car-list.component.html',
    styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
    carService = inject(CarService);
    fileService = inject(FileService);
    authService = inject(AuthService);
    navService = inject(NavigationService);
    modelService = inject(ModelService);
    private cdr = inject(ChangeDetectorRef);

    cars: Car[] = [];
    models: Model[] = [];
    loading: boolean = false;
    message: string | null = null;
    messageType: 'success' | 'error' = 'success';

    // Form state
    showForm: boolean = false;
    isEditing: boolean = false;
    currentCar: any = this.getEmptyCar();
    selectedFile: File | null = null;
    imagePreview: string | null = null;

    ngOnInit(): void {
        this.loadCars();
        this.loadModels();
    }

    loadModels() {
        this.modelService.getAll().subscribe({
            next: (data) => this.models = data,
            error: (e) => console.error('Load models error:', e)
        });
    }

    getEmptyCar() {
        return {
            immatriculation: '',
            couleur: '',
            prixParJour: 0,
            statut: 'DISPONIBLE',
            imageUrl: '',
            modelId: null
        };
    }

    loadCars() {
        this.loading = true;
        this.carService.getCars().subscribe({
            next: (data) => {
                // Sort by id descending (newest first)
                this.cars = data.sort((a, b) => (b.id || 0) - (a.id || 0));
                this.loading = false;
                this.cdr.detectChanges();
            },
            error: (e) => {
                this.loading = false;
                this.showMessage('Erreur lors du chargement des voitures', 'error');
                console.error('Load cars error:', e);
            }
        });
    }

    openAddForm() {
        this.currentCar = this.getEmptyCar();
        this.isEditing = false;
        this.showForm = true;
        this.message = null;
        this.selectedFile = null;
        this.imagePreview = null;
    }

    editCar(car: Car) {
        this.currentCar = {
            ...car,
            modelId: car.model?.id
        };
        this.isEditing = true;
        this.showForm = true;
        this.message = null;
        this.selectedFile = null;
        this.imagePreview = car.imageUrl ? 'http://localhost:8081' + car.imageUrl : null;
    }

    cancelForm() {
        this.showForm = false;
        this.currentCar = this.getEmptyCar();
        this.isEditing = false;
        this.selectedFile = null;
        this.imagePreview = null;
    }

    onFileSelected(event: Event) {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files[0]) {
            this.selectedFile = input.files[0];
            // Create preview
            const reader = new FileReader();
            reader.onload = (e) => {
                this.imagePreview = e.target?.result as string;
            };
            reader.readAsDataURL(this.selectedFile);
        }
    }

    async submitCar() {
        if (!this.currentCar.immatriculation || !this.currentCar.prixParJour) {
            this.showMessage('Veuillez remplir tous les champs obligatoires', 'error');
            return;
        }

        this.loading = true;

        // Upload image first if selected
        if (this.selectedFile) {
            try {
                const uploadResult = await this.fileService.uploadFile(this.selectedFile).toPromise();
                if (uploadResult) {
                    this.currentCar.imageUrl = uploadResult.url;
                }
            } catch (err) {
                this.loading = false;
                this.showMessage('Erreur lors de l\'upload de l\'image', 'error');
                console.error('Upload error:', err);
                return;
            }
        }

        // Now save the car
        if (this.isEditing && this.currentCar.id) {
            this.carService.updateCar(this.currentCar.id, this.currentCar).subscribe({
                next: () => {
                    this.showMessage('Voiture modifiée avec succès', 'success');
                    this.cancelForm();
                    this.loadCars();
                },
                error: (err) => {
                    this.loading = false;
                    this.showMessage('Erreur lors de la modification', 'error');
                    console.error('Update error:', err);
                }
            });
        } else {
            this.carService.createCar(this.currentCar).subscribe({
                next: () => {
                    this.showMessage('Voiture ajoutée avec succès', 'success');
                    this.cancelForm();
                    this.loadCars();
                },
                error: (err) => {
                    this.loading = false;
                    this.showMessage('Erreur lors de l\'ajout', 'error');
                    console.error('Create error:', err);
                }
            });
        }
    }

    deleteCar(id: number | undefined) {
        if (id && confirm('Êtes-vous sûr de vouloir supprimer cette voiture ?')) {
            this.loading = true;
            this.carService.deleteCar(id).subscribe({
                next: () => {
                    this.showMessage('Voiture supprimée', 'success');
                    this.loadCars();
                },
                error: (err) => {
                    this.loading = false;
                    this.showMessage('Erreur lors de la suppression', 'error');
                    console.error('Delete error:', err);
                }
            });
        }
    }

    reserveCar(car: Car) {
        this.navService.switchTab('catalogue');
    }

    getImageUrl(car: Car): string {
        if (car.imageUrl) {
            return 'http://localhost:8081' + car.imageUrl;
        }
        return '';
    }

    showMessage(msg: string, type: 'success' | 'error') {
        this.message = msg;
        this.messageType = type;
        setTimeout(() => this.message = null, 3000);
    }
}
