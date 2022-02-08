import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ViewWillEnter } from '@ionic/angular';

@Component({
  selector: 'app-change-password-page',
  templateUrl: './change-password.page.html',
  styleUrls: ['./change-password.page.scss'],
})
export class ChangePasswordPage implements OnInit, ViewWillEnter {
  resetCode: string;

  constructor(private route: ActivatedRoute) {}
  ionViewWillEnter(): void {
    this.resetCode = this.route.snapshot.paramMap.get('id');
  }

  ngOnInit() {}
}
