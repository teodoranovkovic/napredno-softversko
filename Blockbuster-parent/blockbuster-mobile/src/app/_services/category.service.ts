import { Response } from './../_models/http/response.model';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../_models/category';
import { DomainService } from './domain/domain.service';
@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  url = this.domain.getAddress() + '/category';
  constructor(private http: HttpClient, private domain: DomainService) {}

  getAllCategories() {
    return this.http.get<Response<Category[]>>(this.url + '/all');
  }
}
