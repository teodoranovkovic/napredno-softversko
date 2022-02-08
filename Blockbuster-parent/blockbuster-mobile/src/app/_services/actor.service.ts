import { Observable, of, Subject } from 'rxjs';
import { Actor } from './../_models/actor';
import { DomainService } from './domain/domain.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ActorService {

  private url = this.domain.getAddress() + '/actors';

  private showFavorites: Subject<boolean> = new Subject<boolean>();

  constructor(private http: HttpClient, private domain: DomainService) { }

  getAllActors(): Observable<Actor[]> {
    return this.http.get<Actor[]>(this.url);
  }

  getActors(term: String, count: number, offset: number): Observable<Actor[]> {
    return this.http.get<Actor[]>(this.url+`/search?name=${term.trim()}&count=${count}&offset=${offset}`)
  }

  getActor(id: number):Observable<Actor> {
    return this.http.get<Actor>(this.url+`/${id}`);
  }

  publishFavourtieActors(favorites: boolean) {
    this.showFavorites.next(favorites);
  }

  getFavourtieActorsObservable() {
    return this.showFavorites.asObservable();
  }

}
