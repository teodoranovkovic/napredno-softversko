import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginModalComponent } from './login-modal/login-modal.component';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { ProfilePopoverComponent } from './profile-popover/profile-popover.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { EditPasswordComponent } from './edit-password/edit-password.component';

@NgModule({
  declarations: [
    LoginModalComponent,
    ProfilePopoverComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    EditPasswordComponent,
  ],
  imports: [CommonModule, IonicModule, ReactiveFormsModule, RouterModule],
  exports: [
    LoginModalComponent,
    ProfilePopoverComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    EditPasswordComponent,
  ],
})
export class AuthModule {}
