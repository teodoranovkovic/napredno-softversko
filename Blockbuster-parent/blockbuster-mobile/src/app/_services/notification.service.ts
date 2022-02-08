import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DomainService } from './domain/domain.service';
import { Response } from '../_models/http/response.model';
import { Notification } from './../_models/notification.model';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private url = this.domain.getAddress() + '/usersNotification';

  constructor(private http: HttpClient, private domain: DomainService) {}

  getReadedNotifications(id: number) {
    return this.http.get<Response<Notification[]>>(
      `${this.url}/customer?id=${id}`
    );
  }

  getUnreadNotificationsCount(id: number) {
    return this.http.get<Response<number>>(`${this.url}/unread/count/${id}`);
  }

  readNotification(id: number) {
    return this.http.get(`${this.url}/setRead/${id}`);
  }

  getNotifications(customerId: number, offset: number) {
    return this.http.get<Response<Notification[]>>(
      `${this.url}/customerOffset?customerId=${customerId}&offset=${offset}`
    );
  }

  readAll(customerId: number) {
    return this.http.get<Response<undefined>>(
      `${this.url}/readAll/${customerId}`
    );
  }

  //     readNotifications(id: number){
  //         return this.http.put<Response<string>>(

  //         )
  //     }
}
