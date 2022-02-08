import { FilmsModule } from './../../films/films.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule, IonInfiniteScrollContent } from '@ionic/angular';

import { FilmsPage } from './films.page';
import { FilmsPageRoutingModule } from './films-routing.module';
import { SectionsModule } from 'src/app/_features/sections/sections.module';
import { FilmsComponent } from 'src/app/films/films.component';
import { SharedModule } from 'src/app/_shared/shared.module';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FilmsPageRoutingModule,
    SectionsModule,
    SharedModule,
    FilmsModule
  ],
  declarations: [
    FilmsPage,
  ],
})
export class FilmsPageModule { }
