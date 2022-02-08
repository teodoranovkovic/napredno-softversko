import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { FilmPageRoutingModule } from './film-routing.module';

import { FilmPage } from './film.page';
import { FilmModule } from 'src/app/_features/film/film.module';
import { SectionsModule } from 'src/app/_features/sections/sections.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FilmPageRoutingModule,
    FilmModule,
    SectionsModule,
  ],
  declarations: [FilmPage],
})
export class FilmPageModule {}
