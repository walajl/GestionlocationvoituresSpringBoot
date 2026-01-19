import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { NavigationService } from '../../services/navigation.service';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent {
    authService = inject(AuthService);
    navService = inject(NavigationService);

    credentials = { email: '', password: '' };
    error = signal<string | null>(null);
    loading = signal<boolean>(false);

    onSubmit(form: any) {
        if (form.invalid) {
            this.error.set('Veuillez remplir tous les champs correctement.');
            Object.keys(form.controls).forEach(key => {
                const control = form.controls[key];
                control.markAsTouched();
            });
            return;
        }

        this.loading.set(true);
        this.error.set(null);

        this.authService.login(this.credentials).subscribe({
            next: () => {
                this.loading.set(false);
                // Redirect based on role
                if (this.authService.isAdmin()) {
                    this.navService.switchTab('admin-reservations');
                } else {
                    this.navService.switchTab('catalogue');
                }
            },
            error: (err) => {
                this.loading.set(false);
                if (err.status === 401 || err.status === 403) {
                    this.error.set('Échec de connexion : Email inconnu ou mot de passe incorrect.');
                } else {
                    this.error.set('Une erreur est survenue. Veuillez réessayer plus tard.');
                }
            }
        });
    }
}
