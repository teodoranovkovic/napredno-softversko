import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Auth } from '../_models/auth.model';
import { Response } from '../_models/http/response.model';
import { RentalListItem } from '../_models/rental-list-item.model';
import { RentalRequest } from '../_models/rental-request.model';
import { Rental } from '../_models/rental.model';
import { DomainService } from './domain/domain.service';

@Injectable({
  providedIn: 'root',
})
export class RentalService {
  private url = this.domain.getAddress() + '/rentals';

  constructor(private http: HttpClient, private domain: DomainService) { }

  getRentals(customerId: number) {
    return this.http.get<Response<RentalListItem[]>>(
      `${this.url}/${customerId}`
    );
  }

  rentFilm(filmId: number, storeId: number) {
    // return this.http.get<Response<undefined>>(
    //   `${this.url}/add?customerId=${customerId}&filmId=${filmId}`
    // );
    const auth: Auth = JSON.parse(localStorage.AUTH);
    return this.http.post<Response<undefined>>(`${this.url}/request`, {
      filmId: filmId,
      storeId: storeId,
      customerId: auth.customerId,
    });
  }

  hasRequested(customerId: number, filmId: number) {
    return this.http.get<Response<boolean>>(
      `${this.url}/hasUserRequested?customerId=${customerId}&filmId=${filmId}`
    );
  }

  getRequests(customerId: number, status = 'all') {
    return this.http.get<Response<RentalRequest[]>>(
      `${this.url}/requestsNoStore?customerId=${customerId}&status=${status}`
    );
  }

  cancelRequest(requestId: number) {
    return this.http.get<Response<undefined>>(
      `${this.url}/setStatus?rentalRequestId=${requestId}&status=canceled`
    );
  }
}
