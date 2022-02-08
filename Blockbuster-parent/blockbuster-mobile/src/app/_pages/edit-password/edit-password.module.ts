import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { EditPasswordPageRoutingModule } from './edit-password-routing.module';

import { EditPasswordPage } from './edit-password.page';
import { SharedModule } from 'src/app/_shared/shared.module';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { AuthModule } from 'src/app/_features/auth/auth.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    EditPasswordPageRoutingModule,
    SharedModule,
    SectionsModule,
    AuthModule,
  ],
  declarations: [EditPasswordPage],
})
export class EditPasswordPageModule {}
