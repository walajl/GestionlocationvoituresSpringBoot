import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrandService, Brand } from '../../services/brand.service';

@Component({
    selector: 'app-brand',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './brand.component.html',
    styleUrls: ['./brand.component.css']
})
export class BrandComponent implements OnInit {
    brands: Brand[] = [];
    currentBrand: Brand = { nom: '', pays: '' };
    isEditing: boolean = false;

    constructor(private brandService: BrandService) { }

    ngOnInit(): void {
        this.loadBrands();
    }

    loadBrands() {
        this.brandService.getAll().subscribe(data => this.brands = data);
    }

    onSubmit() {
        if (!this.currentBrand.nom) {
            alert('Veuillez saisir un nom de marque');
            return;
        }

        if (this.isEditing && this.currentBrand.id) {
            this.brandService.update(this.currentBrand.id, this.currentBrand).subscribe(() => {
                this.resetForm();
                this.loadBrands();
            });
        } else {
            this.brandService.create(this.currentBrand).subscribe(() => {
                this.resetForm();
                this.loadBrands();
            });
        }
    }

    editBrand(brand: Brand) {
        this.currentBrand = { ...brand };
        this.isEditing = true;
    }

    deleteBrand(id: number | undefined) {
        if (id && confirm('Supprimer cette marque ?')) {
            this.brandService.delete(id).subscribe(() => this.loadBrands());
        }
    }

    resetForm() {
        this.currentBrand = { nom: '', pays: '' };
        this.isEditing = false;
    }
}
