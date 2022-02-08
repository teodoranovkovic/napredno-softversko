import { FilmCardComponent } from './film-card/film-card.component';
import { FilmSlidesComponent } from './film-slides/film-slides.component';
import { ActorsListBoxComponent } from './actors-list-box/actors-list-box.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DescriptionCutPipe } from './pipes/descriptionCut.pipe';
import { StarPipe } from './pipes/star.pipe';
import { IonicModule } from '@ionic/angular';
import { StarsComponent } from './stars/stars.component';
import { MoneyPipe } from './pipes/money.pipe';
import { FilmsListboxComponent } from './films-listbox/films-listbox.component';
import { SearchFilmsComponent } from './search-films/search-films.component';
import { FilmsCategoriesComponent } from './films-categories/films-categories.component';

import { SortFilmsComponent } from './sort-films/sort-films.component';
import { FormsModule } from '@angular/forms';
import { HumanizeDatePipe } from './pipes/humanize-date.pipe';
import { FilmCategoryPosterComponent } from './film-category-poster/film-category-poster.component';
import { DurationPipe } from './pipes/duration.pipe';
import { ActorCardComponent } from './actor-card/actor-card.component';
import { TabFilterComponent } from './tab-filter/tab-filter.component';
import { ActorListComponent } from './actor-list/actor-list.component';
import { RouterModule } from '@angular/router';
import { FilmPosterComponent } from './film-poster/film-poster.component';

@NgModule({
  imports: [CommonModule, IonicModule, FormsModule, RouterModule],
  declarations: [
    DescriptionCutPipe,
    StarPipe,
    StarsComponent,
    MoneyPipe,
    FilmsListboxComponent,
    SearchFilmsComponent,
    FilmsCategoriesComponent,
    SortFilmsComponent,
    HumanizeDatePipe,
    ActorsListBoxComponent,
    FilmSlidesComponent,
    FilmCardComponent,
    FilmCategoryPosterComponent,
    DurationPipe,
    ActorCardComponent,
    TabFilterComponent,
    ActorListComponent,
    FilmPosterComponent,



  ],
  exports: [
    DescriptionCutPipe,
    StarPipe,
    MoneyPipe,
    FilmsListboxComponent,
    SearchFilmsComponent,
    FilmsCategoriesComponent,
    SortFilmsComponent,
    HumanizeDatePipe,
    ActorsListBoxComponent,
    FilmSlidesComponent,
    FilmCardComponent,
    FilmCategoryPosterComponent,
    ActorCardComponent,
    TabFilterComponent,
    ActorListComponent,
    StarsComponent,
    DurationPipe,
    FilmPosterComponent,


  ],
})
export class SharedModule { }
