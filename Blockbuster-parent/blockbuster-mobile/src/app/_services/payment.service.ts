import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Response } from '../_models/http/response.model';
import { Payment } from '../_models/payment.model';
import { DomainService } from './domain/domain.service';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private url = this.domain.getAddress() + '/payments';
  constructor(private http: HttpClient, private domain: DomainService) { }

  getPayments(customerId: number) {
    return this.http.get<Response<Payment[]>>(
      `${this.url}?customerId=${customerId}`
    );
  }

  getFilteredPayments(customerId: number, age: string, type: string) {
    return this.http.get<Response<Payment[]>>(
      `${this.url}/filtered?customerId=${customerId}&age=${age}&type=${type}`
    );
  }
}
