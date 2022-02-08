import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AlertController, ToastController } from '@ionic/angular';
import { tap } from 'rxjs/operators';
import { Profile } from '../_models/customer/profile.model';
import { Response } from '../_models/http/response.model';
import { DomainService } from './domain/domain.service';
import { PaymentService } from './payment.service';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  errorMessage: string;
  repos: any;

  url = this.domain.getAddress() + '/customers';
  urlEditProfile = this.domain.getAddress() + '/updateCustomer';

  constructor(
    private http: HttpClient,
    private domain: DomainService,
    private alertController: AlertController,
    private toaster: ToastController
  ) {}

  getAllCustomers() {
    return this.http.get<Response<Profile[]>>(this.url + '/all');
  }

  getClient(id: number) {
    return this.http.get<Response<Profile>>(`${this.url}/${id}`);
  }

  addMoney(id: number, amount: number) {
    return this.http.post(`${this.url}/addMoney`, {
      id,
      amount,
    });
  }
  setClient(cust: Profile) {
    this.errorMessage = '';
    const params = new HttpParams();
    const headers = new HttpHeaders({});
    return this.http
      .post(
        'http://praktikanti.dualsoft.net:8080/blockbuster/customers/updateCustomer',
        cust,
        {
          headers: headers,
          params: params,
        }
      )
      .subscribe(
        async (response) => {
          this.repos = response;
          const toast = await this.toaster.create({
            message: 'Successfully edited profile',
            duration: 3000,
          });
          toast.present();
        },
        (error) => {
          this.errorMessage = error;
          this.presentAlert();
        }
      );
  }

  async presentAlert() {
    const alert = await this.alertController.create({
      header: 'Warning',
      subHeader: 'Password warning !',
      message: 'The passwords do not match!',
      buttons: ['OK'],
    });
    await alert.present();
    const { role } = await alert.onDidDismiss();
  }

  selectStore(customerId: number, storeId: number) {
    return this.http.post(`${this.url}/updateStore`, { customerId, storeId });
  }
}
