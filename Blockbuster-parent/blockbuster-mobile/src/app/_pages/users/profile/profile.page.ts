import { Component, ViewChild } from '@angular/core';
import {
  ToastController,
  ViewDidEnter,
  ViewWillEnter,
  ViewWillLeave,
} from '@ionic/angular';
import { Subscribable, Subscription } from 'rxjs';
import { take } from 'rxjs/operators';
import { ProfileComponent } from 'src/app/_features/profile/profile/profile.component';
import { Auth } from 'src/app/_models/auth.model';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements ViewWillEnter, ViewWillLeave {
  auth: Auth;
  lockedFunds: number;
  authSub: Subscription;

  constructor(
    private authService: AuthService,
    private toastController: ToastController
  ) {}
  ionViewWillLeave(): void {
    this.authSub.unsubscribe();
  }

  ionViewWillEnter(): void {
    this.auth = JSON.parse(localStorage.AUTH);
    this.authSub = this.authService.getAuthObservable().subscribe((res) => {
      this.auth = res;
      this.authService
        .getLockedFunds(this.auth.customerId)
        .pipe(take(1))
        .subscribe(
          (res) => {
            this.lockedFunds = res.data;
          },
          (err) => {
            if (err.error.message) {
              this.toastController.create({
                message: err.error.message,
                header: 'Error',
                duration: 3000,
              });
            } else {
              this.toastController.create({
                message: 'Server not responding',
                header: 'Error',
                duration: 2000,
              });
            }
          }
        );
    });
    this.authService
      .getLockedFunds(this.auth.customerId)
      .pipe(take(1))
      .subscribe(
        (res) => {
          this.lockedFunds = res.data;
        },
        (err) => {
          if (err.error.message) {
            this.toastController.create({
              message: err.error.message,
              header: 'Error',
              duration: 3000,
            });
          } else {
            this.toastController.create({
              message: 'Server not responding',
              header: 'Error',
              duration: 2000,
            });
          }
        }
      );
  }
}
