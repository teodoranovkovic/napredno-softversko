import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FavouritesService {

  private favActors: Subject<Map<any,any>> = new Subject<Map<any,any>>();

  constructor() { }

  publishFavourtieActors(favActors: Map<any,any>) {
    this.favActors.next(favActors);
  }

  getFavourtieActorsObservable() {
    return this.favActors.asObservable();
  }
}
