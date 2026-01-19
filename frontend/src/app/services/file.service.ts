import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class FileService {
    private apiUrl = 'http://localhost:8081/api/files';

    constructor(private http: HttpClient) { }

    uploadFile(file: File): Observable<{ url: string; filename: string }> {
        const formData = new FormData();
        formData.append('file', file);
        return this.http.post<{ url: string; filename: string }>(`${this.apiUrl}/upload`, formData);
    }

    deleteFile(filename: string): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${filename}`);
    }
}
