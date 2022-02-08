import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class HttpSessionInterceptorService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // throw new Error('Method not implemented.');
    console.log('intercepted request ... ');

    // const sessionRequest = req.clone({
    //   withCredentials: true
    // });

    console.log('Sending request with new header now ...');


    let headers = {};
    headers['x-auth-token'] = "test-andjela";

    let params = {};
    params['utkn'] = "user-token-andjelaangie";

    let newRequestData = {};
    // if (headers != null) {
    //   newRequestData = newRequestData == null ? {} : newRequestData;
    //   newRequestData['setHeaders'] = headers;
    // }

    // if (params != null) {
    //   newRequestData = newRequestData == null ? {} : newRequestData;
    //   newRequestData['setParams'] = params;
    // }

   // newRequestData['withCredentials'] = 'true';

    const sessionRequest = params != null ? req.clone(newRequestData) : req.clone();


    // send the newly created request
    return next.handle(sessionRequest).pipe(
      tap((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) {
          console.log(event);
        }
      }, (errEvent: any) => {
        console.log(errEvent);
      })
    );
  }
}
