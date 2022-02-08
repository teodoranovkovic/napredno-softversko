import { Component, Input, OnInit } from '@angular/core';
import { ModalController, ToastController } from '@ionic/angular';
import { Auth } from 'src/app/_models/auth.model';
import { AddMoneyModalComponent } from '../add-money-modal/add-money-modal.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  @Input() auth: Auth;
  private backgrounds = [
    '../../../../assets/profile/pulp-bg1.jpg',
    '../../../../assets/profile/pulp-bg2.jpg',
    '../../../../assets/profile/fight-bg1.jpg',
    '../../../../assets/profile/fight-bg2.jpg',
    '../../../../assets/profile/departed-bg1.jpg',
    '../../../../assets/profile/harry-bg1.jpg',
    '../../../../assets/profile/memento-bg1.jpg',
    '../../../../assets/profile/wolf-bg1.jpg',
    '../../../../assets/profile/wolf-bg2.jpg',
  ];
  background =
    this.backgrounds[Math.floor(Math.random() * this.backgrounds.length)];

  @Input() lockedFunds: number;

  constructor(
    private modalController: ModalController,
    private toastController: ToastController
  ) {}

  ngOnInit() {}

  async addMoney() {
    const modal = this.modalController.create({
      component: AddMoneyModalComponent,
      animated: true,
      componentProps: { balance: this.auth.balance },
    });
    (await modal).onDidDismiss().then(async (ret) => {
      if (ret.data) this.auth.balance += ret.data;
      const toast = await this.toastController.create({
        message: `${Number(ret.data).toFixed(2)}$ added`,
        duration: 3000,
        position: 'top',
        buttons: [
          {
            text: 'Okay',
            role: 'cancel',
          },
        ],
      });
      await toast.present();
    });
    return await (await modal).present();
  }

  getBackground() {
    return this.background;
  }
}
