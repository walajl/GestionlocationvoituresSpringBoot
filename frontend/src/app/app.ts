import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';
import { NavigationService } from './services/navigation.service';

// Components
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { MyReservationsComponent } from './components/my-reservations/my-reservations.component';
import { AdminComponent } from './components/admin/admin.component';
import { CarListComponent } from './components/car-list/car-list.component';
import { UserComponent } from './components/user/user.component';
import { BrandComponent } from './components/brand/brand.component';
import { ModelComponent } from './components/model/model.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent,
    CatalogueComponent,
    MyReservationsComponent,
    AdminComponent,
    CarListComponent,
    UserComponent,
    BrandComponent,
    ModelComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  authService = inject(AuthService);
  navService = inject(NavigationService);
  // Force rebuild trigger

  title = 'Location Voitures';

  logout() {
    this.authService.logout();
    this.navService.switchTab('home');
  }
}
