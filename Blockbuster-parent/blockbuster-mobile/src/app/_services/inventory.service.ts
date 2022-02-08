import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StoreWithInventory } from '../_models/store-with-inventory.model';
import { DomainService } from './domain/domain.service';
import { Response } from '../_models/http/response.model';

@Injectable({
  providedIn: 'root',
})
export class InventoryService {
  private url = this.domain.getAddress() + '/inventory';
  constructor(private http: HttpClient, private domain: DomainService) {}

  getStoresWithInventory(filmId: number) {
    return this.http.get<Response<StoreWithInventory[]>>(
      `${this.url}/filmInventoryPerStore?filmId=${filmId}`
    );
  }
}
