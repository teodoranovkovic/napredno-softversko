import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ChangePasswordPageRoutingModule } from './change-password-routing.module';

import { ChangePasswordPage } from './change-password.page';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { AuthModule } from 'src/app/_features/auth/auth.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ChangePasswordPageRoutingModule,
    SectionsModule,
    AuthModule,
  ],
  declarations: [ChangePasswordPage],
})
export class ChangePasswordPageModule {}
