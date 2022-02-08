import { tap } from 'rxjs/operators';
import { Customer } from './../_models/customer.model';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Auth } from '../_models/auth.model';
import { Response } from '../_models/http/response.model';
import { DomainService } from './domain/domain.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authSubject: Subject<Auth> = new Subject<Auth>();

  url = this.domain.getAddress() + '/auth';

  constructor(private http: HttpClient, private domain: DomainService) {}

  login(email: string, password: string): Observable<Response<Auth>> {
    return this.http.post<Response<Auth>>(
      `${this.url}/login`,
      { email, password },
      {
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
  }

  publishAuth(auth: Auth) {
    this.authSubject.next(auth);
  }

  getAuthObservable() {
    return this.authSubject.asObservable();
  }

  register(customer: Customer): Observable<Response<Auth>> {
    return this.http
      .post<Response<Auth>>(`${this.url}/register`, customer, {
        withCredentials: true,
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      })
      .pipe(tap(console.log));
  }

  requestPasswordReset(email: string) {
    return this.http.post(
      `${this.domain.getAddress()}/mail/changePassword`,
      email
    );
  }

  passwordReset(code: string, password: string) {
    return this.http.post(`${this.url}/changePassword`, { password, code });
  }

  editPassword(customerId: number, oldPassword: string, newPassword: string) {
    return this.http.post(`${this.url}/editPassword`, {
      customerId,
      oldPassword,
      newPassword,
    });
  }

  getLockedFunds(customerId: number) {
    return this.http.get<Response<number>>(
      `${this.url}/lockedFunds/${customerId}`
    );
  }
}
