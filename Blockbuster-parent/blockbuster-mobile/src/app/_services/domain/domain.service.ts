import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from 'src/app/app-config';

@Injectable({
  providedIn: 'root',
})
export class DomainService {
  constructor(private http: HttpClient) {}

  public getAddress() {
    return AppConfig.API_ENDPOINT;
    // return AppConfig.API_ENDPOINT_DEV;
  }

  public login() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
      }),
      //    withCredentials: true,
      observe: 'response' as 'response',
    };

    let body = new HttpParams({})
      .set('j_username', 'andjela')
      .set('j_password', 'test');

    this.http
      .post(this.getAddress() + '/actors/login', body.toString(), httpOptions)
      .subscribe((data) => {});
  }
}
