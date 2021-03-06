import { SharedModule } from './../../_shared/shared.module';
import { SectionsModule } from './../../_features/sections/sections.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ActorsPageRoutingModule } from './actors-routing.module';

import { ActorsPage } from './actors.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ActorsPageRoutingModule,
    SectionsModule,
    SharedModule,
  ],
  declarations: [ActorsPage]
})
export class ActorsPageModule {}
