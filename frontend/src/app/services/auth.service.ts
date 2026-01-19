import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    private apiUrl = 'http://localhost:8081/api/auth';

    currentUser = signal<any>(null);
    token = signal<string | null>(localStorage.getItem('token'));

    constructor(private http: HttpClient) {
        const savedUser = localStorage.getItem('user');
        if (savedUser) {
            this.currentUser.set(JSON.parse(savedUser));
        }
    }

    login(credentials: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/login`, credentials).pipe(
            tap(res => {
                this.token.set(res.token);
                this.currentUser.set(res.user);
                localStorage.setItem('token', res.token);
                localStorage.setItem('user', JSON.stringify(res.user));
            })
        );
    }

    register(user: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/register`, user);
    }

    logout() {
        this.token.set(null);
        this.currentUser.set(null);
        localStorage.removeItem('token');
        localStorage.removeItem('user');
    }

    isAuthenticated(): boolean {
        return !!this.token();
    }

    isAdmin(): boolean {
        return this.currentUser()?.role === 'ADMIN';
    }
}
