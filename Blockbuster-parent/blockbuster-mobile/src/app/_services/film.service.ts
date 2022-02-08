import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { FilmForSlider } from 'src/app/_models/filmsForSlider';
import { Auth } from '../_models/auth.model';
import { FilmDetails } from '../_models/film-details.model';
import { FilmForList } from '../_models/filmsForList';
import { Response } from '../_models/http/response.model';
import { DomainService } from './domain/domain.service';

@Injectable({
  providedIn: 'root',
})
export class FilmService {
  private url = this.domain.getAddress() + '/films';
  constructor(private http: HttpClient, private domain: DomainService) { }

  getFilmForSlider() {
    return this.http.get<FilmForSlider[]>(this.url + '/filmsForSlider');
  }

  getTopRentedFilmForSlider() {
    return this.http.get<FilmForSlider[]>(this.url + '/topRentedForSlider');
  }

  getNewFilmsForSlider() {
    return this.http.get<FilmForSlider[]>(this.url + '/newFilmsForSlider');
  }

  getSuggestion(customerId: number) {
    return this.http.get<FilmForSlider[]>(
      this.url + '/suggestion?customerId=' + customerId
    );
  }

  getFilmByCategoryForSlider(category) {
    return this.http.get<FilmForSlider[]>(
      `${this.url}/category/?category=${category}`
    );
  }

  getFilmsForList() {
    return this.http.get<FilmForList[]>(this.url + '/filmsForList');
  }

  getFilmsForType(type: string) {
    let auth: Auth = undefined;
    if (localStorage.AUTH) {
      auth = JSON.parse(localStorage.AUTH);
    }

    switch (type) {
      case 'for you':
        if (auth) {
          return this.getSuggestion(auth.customerId);
        }
        return this.getTopRentedFilmForSlider();
      case 'most wanted':
        return this.getTopRentedFilmForSlider();
      case 'new movies':
        return this.getNewFilmsForSlider();
      case 'top rated movies':
        return this.getFilmForSlider();
      case 'category':
        return this.getFilmForSlider();
      default:
        break;
    }
  }

  getFilmsByCategory(id: number) {
    return this.http.get<FilmForList[]>(`${this.url}/category/${id}`);
  }

  getFilmsForActor(id: number) {
    return this.http.get<FilmForList[]>(this.url + `/actor?id=${id}`);
  }

  getFilmDetails(filmId: number, storeId: number) {
    return this.http.get<Response<FilmDetails>>(
      `${this.url}/details?filmId=${filmId}&storeId=${storeId}`
    );
  }
}
