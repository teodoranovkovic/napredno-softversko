import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProfilePageRoutingModule } from './profile-routing.module';

import { ProfilePage } from './profile.page';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { SharedModule } from 'src/app/_shared/shared.module';
import { ProfileModule } from 'src/app/_features/profile/profile.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ProfilePageRoutingModule,
    SectionsModule,
    SharedModule,
    ProfileModule,
  ],
  declarations: [ProfilePage],
})
export class ProfilePageModule {}
