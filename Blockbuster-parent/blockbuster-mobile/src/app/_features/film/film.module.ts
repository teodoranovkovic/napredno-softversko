import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FilmDetailComponent } from './film-detail/film-detail.component';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { SharedModule } from 'src/app/_shared/shared.module';
import { SelectStoreModalComponent } from './select-store-modal/select-store-modal.component';

@NgModule({
  declarations: [FilmDetailComponent, SelectStoreModalComponent],
  imports: [CommonModule, IonicModule, RouterModule, SharedModule],
  exports: [FilmDetailComponent, SelectStoreModalComponent],
})
export class FilmModule {}
