import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PopoverController } from '@ionic/angular';
import { Auth } from 'src/app/_models/auth.model';
import { AuthService } from 'src/app/_services/auth.service';
import { NotificationsPopoverComponent } from '../../sections/notifications-popover/notifications-popover.component';

@Component({
  selector: 'app-profile-popover',
  templateUrl: './profile-popover.component.html',
  styleUrls: ['./profile-popover.component.scss'],
})
export class ProfilePopoverComponent implements OnInit {
  auth: Auth;
  constructor(
    private authService: AuthService,
    private popoverController: PopoverController,
    private router: Router
  ) {}

  ngOnInit() {
    this.auth = JSON.parse(localStorage.getItem('AUTH'));
  }

  async logout() {
    localStorage.removeItem('AUTH');
    this.authService.publishAuth(null);
    this.router.navigate(['home']);
    await this.popoverController.dismiss();
  }

  async close() {
    await this.popoverController.dismiss();
  }

  openNotifications(event) {
    this.popoverController
      .create({
        component: NotificationsPopoverComponent,
        event: event,
      })
      .then((p) => p.present());
  }
}
