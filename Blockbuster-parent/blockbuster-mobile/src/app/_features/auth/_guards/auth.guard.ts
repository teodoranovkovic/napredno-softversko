import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { Observable } from 'rxjs';
import { Auth } from 'src/app/_models/auth.model';
import { AuthService } from 'src/app/_services/auth.service';
import { LoginModalComponent } from '../login-modal/login-modal.component';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return true;
  }
  constructor(private _auth: AuthService,
    private modalController: ModalController,

    private _router: Router) { }

  canActive(): boolean {

    const auth: Auth = JSON.parse(localStorage.AUTH);
    if (auth) {
      return true;
    }
    else {
      this.openLoginModal();
      return false;
    }
  }

  async openLoginModal() {
    const modal = this.modalController.create({
      component: LoginModalComponent,
    });
    return await (await modal).present();
  }
}

