import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { take } from 'rxjs/operators';
import { Auth } from 'src/app/_models/auth.model';
import { NotificationService } from 'src/app/_services/notification.service';
import { RealTimeService } from 'src/app/_services/real-time.service';
import { Notification } from './../../../_models/notification.model';
// import { PopoverController } from '@ionic/angular';

@Component({
  selector: 'app-notifications-popover',
  templateUrl: './notifications-popover.component.html',
  styleUrls: ['./notifications-popover.component.scss'],
})
export class NotificationsPopoverComponent implements OnInit {
  auth: Auth = undefined;
  notifications: Notification[] = [];
  offset = 0;
  noMoreNotifications = false;
  unreadNotificationsCount: number;

  constructor(
    private notificationService: NotificationService,
    private realTime: RealTimeService,
    private toast: ToastController,
    private router: Router
  ) {}

  ngOnInit() {
    const auth = localStorage.AUTH;
    if (auth) {
      this.auth = JSON.parse(auth);
      this.notificationService
        .getNotifications(this.auth.customerId, 0)
        .pipe(take(1))
        .subscribe(
          (res) => {
            this.notifications = res.data;
            this.offset += res.data.length;
          },
          (err) => {
            if (err.error.message) {
              this.toast
                .create({ message: err.error.message, duration: 3000 })
                .then((t) => t.present());
            } else {
              this.toast
                .create({ message: 'Server not responding', duration: 2000 })
                .then((t) => t.present());
            }
          }
        );
    }

    this.notificationService
      .getUnreadNotificationsCount(this.auth.customerId)
      .pipe(take(1))
      .subscribe((res) => {
        this.unreadNotificationsCount = res.data;
      });

    this.realTime.getNotificationsCountObservable().subscribe((count) => {
      this.unreadNotificationsCount = count;
    });

    this.realTime.getNotificationsObservable().subscribe((notification) => {
      this.notifications.unshift(notification);
    });
  }

  readNotification(notification: Notification) {
    this.notificationService
      .readNotification(notification.userNotificationId)
      .pipe(take(1))
      .subscribe(
        (res) => {
          notification.read = true;

          this.realTime.publishNotificationsCount(
            --this.unreadNotificationsCount
          );
        },
        (err) => {
          if (err.error.message) {
            this.toast
              .create({ message: err.error.message, duration: 3000 })
              .then((t) => t.present());
          } else {
            this.toast
              .create({ message: 'Server not responding', duration: 2000 })
              .then((t) => t.present());
          }
        }
      );
  }

  notificationClick(notification: Notification) {
    this.readNotification(notification);
    switch (notification.title) {
      case 'Request accepted':
        this.router.navigate(['rental-requests']);
        break;
      case 'Request canceled':
        this.router.navigate(['rental-requests']);
        break;
      default:
        break;
    }
  }

  loadMore() {
    this.notificationService
      .getNotifications(this.auth.customerId, this.offset)
      .pipe(take(1))
      .subscribe(
        (res) => {
          if (res.data.length === 0) {
            this.toast
              .create({ message: 'No more notifications!', duration: 2000 })
              .then((t) => t.present());
            this.noMoreNotifications = true;
            return;
          }
          this.notifications = [...this.notifications, ...res.data];
          this.offset += res.data.length;
        },
        (err) => {
          if (err.error.message) {
            this.toast
              .create({ message: err.error.message })
              .then((t) => t.present());
          } else {
            this.toast
              .create({ message: 'Server not responding' })
              .then((t) => t.present());
          }
        }
      );
  }

  readAll() {
    this.notificationService
      .readAll(this.auth.customerId)
      .pipe(take(1))
      .subscribe(
        (res) => {
          this.notifications.forEach((n) => (n.read = true));
          this.unreadNotificationsCount = 0;
          this.realTime.publishNotificationsCount(0);
        },
        (err) => {
          if (err.error.message) {
            this.toast
              .create({ message: err.error.message, duration: 3000 })
              .then((t) => t.present());
          } else {
            this.toast
              .create({ message: 'Server not responding', duration: 2000 })
              .then((t) => t.present());
          }
        }
      );
  }
}
