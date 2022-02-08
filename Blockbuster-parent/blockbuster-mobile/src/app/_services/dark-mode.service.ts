import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DarkModeService {

  private darkModeSubject: Subject<boolean> = new Subject<boolean>();

  constructor() { }

  publishAuth(darkMode: boolean) {
    this.darkModeSubject.next(darkMode);
  }

  getAuthObservable() {
    return this.darkModeSubject.asObservable();
  }
}
