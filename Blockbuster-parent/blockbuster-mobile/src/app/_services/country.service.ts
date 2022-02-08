import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Country } from '../_models/country.model'
import { DomainService } from './domain/domain.service';

@Injectable({
  providedIn: 'root',
})

export class CountryService {
  private url = this.domain.getAddress() + '/countries';
  constructor(private http: HttpClient, private domain: DomainService) { }

  getCountries() {
    return this.http.get<Country[]>(this.url);
  }

  getCities(id: number) {
    return this.http.get<[]>(`${this.url}/getCities/${id}`);
  }
}
