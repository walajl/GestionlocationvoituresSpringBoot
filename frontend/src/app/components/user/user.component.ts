import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService, User } from '../../services/user.service';

@Component({
    selector: 'app-user',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    users: User[] = [];
    currentUser: User = { nom: '', email: '', role: 'CLIENT' };
    isEditing: boolean = false;
    showForm: boolean = false;
    loading: boolean = false;
    message: string | null = null;
    messageType: 'success' | 'error' = 'success';

    constructor(
        private userService: UserService,
        private cdr: ChangeDetectorRef
    ) { }

    // Chargement initial des utilisateurs
    ngOnInit(): void {
        this.loadUsers();
    }

    // Récupère la liste des utilisateurs depuis le backend
    loadUsers() {
        this.loading = true;
        this.userService.getAll().subscribe({
            next: (data) => {
                this.users = data;
                this.loading = false;
                this.cdr.detectChanges();
            },
            error: (err) => {
                this.loading = false;
                this.showMessage('Erreur lors du chargement des utilisateurs', 'error');
                console.error('Load users error:', err);
            }
        });
    }

    // Gestion de la soumission du formulaire (Création ou Mise à jour)
    onSubmit(form: any) {
        if (form.invalid) {
            this.showMessage('Veuillez remplir tous les champs obligatoires', 'error');
            Object.keys(form.controls).forEach(key => {
                const control = form.controls[key];
                control.markAsTouched();
            });
            return;
        }

        this.loading = true;

        if (this.isEditing && this.currentUser.id) {
            // Mode Modification
            this.userService.update(this.currentUser.id, this.currentUser).subscribe({
                next: () => {
                    this.showMessage('Utilisateur modifié avec succès', 'success');
                    this.resetForm();
                    this.loadUsers();
                },
                error: (err) => {
                    this.loading = false;
                    this.showMessage('Erreur lors de la modification', 'error');
                    console.error('Update error:', err);
                }
            });
        } else {
            // Mode Création
            this.userService.create(this.currentUser).subscribe({
                next: () => {
                    this.showMessage('Utilisateur créé avec succès', 'success');
                    this.resetForm();
                    this.loadUsers();
                },
                error: (err) => {
                    this.loading = false;
                    this.showMessage('Erreur lors de la création', 'error');
                    console.error('Create error:', err);
                }
            });
        }
    }

    // Prépare le formulaire pour l'édition d'un utilisateur existant
    editUser(user: User) {
        this.currentUser = { ...user };
        this.isEditing = true;
        this.message = null;
    }

    // Supprime un utilisateur après confirmation
    deleteUser(id: number | undefined) {
        if (id && confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')) {
            this.loading = true;
            this.userService.delete(id).subscribe({
                next: () => {
                    this.showMessage('Utilisateur supprimé', 'success');
                    this.loadUsers();
                },
                error: (err) => {
                    this.loading = false;
                    this.showMessage('Erreur lors de la suppression', 'error');
                    console.error('Delete error:', err);
                }
            });
        }
    }

    // Réinitialise le formulaire
    resetForm() {
        this.currentUser = { nom: '', email: '', role: 'CLIENT' };
        this.isEditing = false;
        this.loading = false;
    }

    // Affiche un message temporaire (succès ou erreur)
    showMessage(msg: string, type: 'success' | 'error') {
        this.message = msg;
        this.messageType = type;
        setTimeout(() => this.message = null, 3000);
    }
}
