import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { NavigationService } from '../../services/navigation.service';

@Component({
    selector: 'app-register',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent {
    authService = inject(AuthService);
    navService = inject(NavigationService);

    user = {
        nom: '',
        prenom: '',
        email: '',
        password: '',
        telephone: '',
        adresse: ''
    };

    confirmPassword = '';
    error = signal<string | null>(null);
    success = signal<boolean>(false);
    loading = signal<boolean>(false);

    // Méthode appelée lors de la soumission du formulaire
    onSubmit(form: any) {
        // Vérification de la validité du formulaire
        if (form.invalid) {
            this.error.set('Veuillez remplir tous les champs correctement.');
            Object.keys(form.controls).forEach(key => {
                const control = form.controls[key];
                control.markAsTouched();
            });
            return;
        }

        // Vérification que les mots de passe correspondent
        if (this.user.password !== this.confirmPassword) {
            this.error.set('Les mots de passe ne correspondent pas');
            return;
        }

        this.loading.set(true);
        this.error.set(null);

        // Appel au service d'inscription
        this.authService.register(this.user).subscribe({
            next: () => {
                // Succès : arrêt du chargement et affichage du message de succès
                this.loading.set(false);
                this.success.set(true);
            },
            error: (err) => {
                // Erreur : affichage du message d'erreur
                this.loading.set(false);
                if (err.status === 409) {
                    this.error.set('Cet email est déjà utilisé. Veuillez en choisir un autre.');
                } else {
                    this.error.set('Erreur lors de l\'inscription. Veuillez vérifier vos informations.');
                }
            }
        });
    }

    // Filtre l'entrée pour ne garder que les chiffres (pour le téléphone)
    onPhoneInput(event: any) {
        const input = event.target;
        input.value = input.value.replace(/[^0-9]/g, '');
        this.user.telephone = input.value;
    }
}
