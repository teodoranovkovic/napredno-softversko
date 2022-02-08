import { RegisterModule } from './../../_features/register/register.component.module';
import { SectionsModule } from './../../_features/sections/sections.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { RegisterPageRoutingModule } from './register-routing.module';
import { RegisterPage } from './register.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RegisterPageRoutingModule,
    SectionsModule,
    ReactiveFormsModule,
    RegisterModule
  ],
  declarations: [RegisterPage]
})
export class RegisterPageModule {}
