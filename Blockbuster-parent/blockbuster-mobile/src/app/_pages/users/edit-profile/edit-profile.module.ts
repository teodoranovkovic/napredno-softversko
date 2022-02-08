import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';


import { EditProfilePage } from './edit-profile.page';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { SharedModule } from 'src/app/_shared/shared.module';
import { EditProfilePageRoutingModule } from './edit-profile-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReactiveFormsModule,
    EditProfilePageRoutingModule,
    SectionsModule,
    SharedModule
  ],
  declarations: [EditProfilePage]
})
export class EditProfilePageModule { }
