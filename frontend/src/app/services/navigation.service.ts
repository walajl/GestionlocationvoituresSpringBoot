import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class NavigationService {
    public currentTab$ = new BehaviorSubject<string>('home');
    public selectedCarId$ = new BehaviorSubject<number | null>(null);

    // Helper to get current value synchronously if needed
    get currentTab(): string {
        return this.currentTab$.value;
    }

    switchTab(tab: string, carId?: number) {
        this.currentTab$.next(tab);
        if (carId) {
            this.selectedCarId$.next(carId);
        }
    }
}
