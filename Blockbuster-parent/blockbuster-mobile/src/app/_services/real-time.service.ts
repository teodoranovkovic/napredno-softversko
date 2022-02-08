import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { RentalRequest } from '../_models/rental-request.model';
import { Notification } from '../_models/notification.model';
import { DomainService } from './domain/domain.service';

@Injectable({
  providedIn: 'root',
})
export class RealTimeService {
  socket;
  stompClient;

  private notificationSubject = new Subject<Notification>();

  private notificationsCountSubject = new Subject<number>();

  constructor(private domain: DomainService) {
    this.socket = new SockJS(
      // 'http://praktikanti.dualsoft.net:8080/blockbuster/ws'
      `${this.domain.getAddress()}/ws`
    );

    const stompClient = Stomp.over(this.socket);
    stompClient.connect({}, () => {
      this.stompClient = stompClient;
      const that = this;
      // stompClient.send('/mobile', null, 'CAO');
      stompClient.subscribe('/mobile', function (messageOutput) {
        const notif = JSON.parse(messageOutput.body) as Notification;
        that.publishNotification(notif);
      });
    });
  }

  getNotificationsObservable() {
    return this.notificationSubject.asObservable();
  }

  publishNotification(notification: Notification) {
    this.notificationSubject.next(notification);
  }

  getNotificationsCountObservable() {
    return this.notificationsCountSubject.asObservable();
  }

  publishNotificationsCount(count: number) {
    this.notificationsCountSubject.next(count);
  }
}
