import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Brand {
    id?: number;
    nom: string;
    pays: string;
}

@Injectable({
    providedIn: 'root'
})
export class BrandService {
    private apiUrl = 'http://localhost:8081/brands';

    constructor(private http: HttpClient) { }

    getAll(): Observable<Brand[]> {
        return this.http.get<Brand[]>(this.apiUrl);
    }

    create(brand: Brand): Observable<Brand> {
        return this.http.post<Brand>(this.apiUrl, brand);
    }

    update(id: number, brand: Brand): Observable<Brand> {
        return this.http.put<Brand>(`${this.apiUrl}/${id}`, brand);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
