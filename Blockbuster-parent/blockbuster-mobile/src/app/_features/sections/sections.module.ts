
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SideMenuComponent } from './side-menu/side-menu.component';
import { IonicModule } from '@ionic/angular';
import { AuthModule } from '../auth/auth.module';
import { NotificationsPopoverComponent } from './notifications-popover/notifications-popover.component';

@NgModule({
  declarations: [
    SideMenuComponent,
    HeaderComponent,
    FooterComponent,
    NotificationsPopoverComponent
  ],
  imports: [
    CommonModule,
    IonicModule,
    RouterModule,
    AuthModule,
    FormsModule
  ],
  exports: [
    SideMenuComponent,
    HeaderComponent,
    FooterComponent,
    NotificationsPopoverComponent
  ],
})
export class SectionsModule { }
