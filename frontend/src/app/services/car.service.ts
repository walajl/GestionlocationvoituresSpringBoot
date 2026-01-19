import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../models/car';

@Injectable({
    providedIn: 'root'
})
export class CarService {
    private apiUrl = 'http://localhost:8081/cars';

    constructor(private http: HttpClient) { }

    getCars(): Observable<Car[]> {
        return this.http.get<Car[]>(this.apiUrl);
    }

    getCar(id: number): Observable<Car> {
        return this.http.get<Car>(`${this.apiUrl}/${id}`);
    }

    createCar(car: any): Observable<Car> {
        return this.http.post<Car>(this.apiUrl, car);
    }

    updateCar(id: number, car: any): Observable<Car> {
        return this.http.put<Car>(`${this.apiUrl}/${id}`, car);
    }

    deleteCar(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }

    searchCars(brand: string, model: string): Observable<Car[]> {
        const url = `${this.apiUrl}/search?brand=${brand}&model=${model}`;
        return this.http.get<Car[]>(url);
    }
}
