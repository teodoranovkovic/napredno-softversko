import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DomainService } from './domain/domain.service';
import { Store } from '../_models/store.model'


@Injectable({
  providedIn: 'root'
})
export class StoreService {

  private url = this.domain.getAddress() + '/stores';
  constructor(private http: HttpClient, private domain: DomainService) { }

  getStores() {
    return this.http.get<Store[]>(this.url);
  }

  getLocalStores(id: number) {
    return this.http.get<Store[]>(`${this.url}/local/${id}`);
  }
}
