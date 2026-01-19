import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Brand } from './brand.service';

export interface Model {
    id?: number;
    nom: string;
    annee: number;
    brand: Brand;
}

@Injectable({
    providedIn: 'root'
})
export class ModelService {
    private apiUrl = 'http://localhost:8081/models';

    constructor(private http: HttpClient) { }

    getAll(): Observable<Model[]> {
        return this.http.get<Model[]>(this.apiUrl);
    }

    create(model: Model): Observable<Model> {
        return this.http.post<Model>(this.apiUrl, model);
    }

    update(id: number, model: Model): Observable<Model> {
        return this.http.put<Model>(`${this.apiUrl}/${id}`, model);
    }

    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
