import { NotificationsPopoverComponent } from './../notifications-popover/notifications-popover.component';

import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import {
  ModalController,
  PopoverController,
  ToastController,
  ViewWillLeave,
} from '@ionic/angular';
import { Auth } from 'src/app/_models/auth.model';
import { AuthService } from 'src/app/_services/auth.service';
import { LoginModalComponent } from '../../auth/login-modal/login-modal.component';
import { ProfilePopoverComponent } from '../../auth/profile-popover/profile-popover.component';
import { Location } from '@angular/common';
import { RealTimeService } from 'src/app/_services/real-time.service';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/_services/customer.service';
import { take } from 'rxjs/operators';
import { NotificationService } from 'src/app/_services/notification.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  auth: Auth = undefined;
  notificationsCount: number = 0;

  @Input()
  subheader: String;

  @Input()
  backButton: boolean = false;

  notifSub: Subscription;

  constructor(
    private modalController: ModalController,
    private popoverController: PopoverController,
    private authService: AuthService,
    private _location: Location,
    private realTime: RealTimeService,
    private toast: ToastController,
    private router: Router,
    private customerService: CustomerService,
    private notifications: NotificationService
  ) {}
  ngOnDestroy(): void {
    console.log(this.notifSub);
    this.notifSub.unsubscribe();
  }

  ngOnInit() {
    const auth = localStorage.AUTH;

    this.authService.getAuthObservable().subscribe((auth) => {
      this.auth = auth;

      this.notifications
        .getUnreadNotificationsCount(this.auth.customerId)
        .pipe(take(1))
        .subscribe((res) => {
          this.notificationsCount = res.data;
        });
    });

    this.realTime.getNotificationsCountObservable().subscribe((count) => {
      this.notifications
        .getUnreadNotificationsCount(this.auth.customerId)
        .pipe(take(1))
        .subscribe((res) => {
          this.notificationsCount = res.data;
        });
    });

    if (auth) {
      this.auth = JSON.parse(auth);
      this.customerService
        .getClient(this.auth.customerId)
        .pipe(take(1))
        .subscribe((res) => {
          this.auth = res.data;
          localStorage.setItem('AUTH', JSON.stringify(this.auth));
          this.authService.publishAuth(this.auth);
        });

      if (this.notifSub) this.notifSub.unsubscribe();

      this.notifSub = this.realTime
        .getNotificationsObservable()
        .subscribe((notification) => {
          if (this.auth.customerId !== notification.customerId) return;
          this.realTime.publishNotificationsCount(++this.notificationsCount);
          this.toast
            .create({
              header: notification.title,
              message: notification.notificationText,
              duration: 5000,
              buttons: [
                {
                  text: 'Ok',
                  role: 'cancel',
                },
                {
                  text: 'View Rentals',
                  handler: () => {
                    this.router.navigate(['rentals']);
                  },
                },
              ],
            })
            .then((t) => t.present());
        });
    }
  }

  async openLoginModal() {
    const modal = this.modalController.create({
      // presentingElement: this.routerOutlet.nativeEl,
      component: LoginModalComponent,
      // componentProps: {
      //   rootPage: AppComponent,
      // },
    });
    return await (await modal).present();
  }

  async openProfilePopover(event: Event) {
    const popover = await this.popoverController.create({
      component: ProfilePopoverComponent,
      // cssClass: 'my-custom-class',
      event: event,
      translucent: true,
      animated: true,
    });
    await popover.present();
  }

  backClicked() {
    this._location.back();
  }

  openNotifications(event: Event) {
    this.popoverController
      .create({
        component: NotificationsPopoverComponent,
        cssClass: 'popover_setting',
        event: event,
        translucent: true,
        animated: true,
      })
      .then((p) => p.present());
  }
}
