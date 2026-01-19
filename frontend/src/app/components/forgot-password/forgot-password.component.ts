import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavigationService } from '../../services/navigation.service';

@Component({
    selector: 'app-forgot-password',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './forgot-password.component.html',
    styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
    navService = inject(NavigationService);

    email = '';
    sent = signal<boolean>(false);

    onSubmit() {
        // Simule l'envoi d'email
        this.sent.set(true);
    }
}
