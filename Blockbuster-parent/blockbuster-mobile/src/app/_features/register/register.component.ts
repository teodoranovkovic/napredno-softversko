import { Country } from './../../_models/country.model';
import { Customer } from './../../_models/customer.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/_services/auth.service';
import { CountryService } from 'src/app/_services/country.service';
import { of, throwError } from 'rxjs';
import { catchError, take } from 'rxjs/operators';
import { ErrorResponse } from 'src/app/_models/http/error-responser.model';
import { ComparePassword } from './../../_shared/validators/repassword.directive';
import { Router } from '@angular/router';
import { ToastController } from '@ionic/angular';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  customerData: Customer;
  countries: Country[];
  cities;
  error: string = undefined;
  loading = false;
  submited = false;

  passwordType: string = 'password';
  repasswordType: string = 'password';

  customer: FormGroup = this.fb.group(
    {
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.pattern('^(?=.*[A-Za-z])[A-Za-z\\d_]{6,}$'),
        ],
      ],
      email: ['', [Validators.required, Validators.email]],

      country: [0, Validators.required],
      district: ['', Validators.required],
      city: [0, Validators.required],
      postalCode: ['', Validators.required],
      street: ['', Validators.required],

      password: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^(?=.*[A-Za-z])(?=.*[@$!%*#?&_])[A-Za-z\d@$!%*#?&_]{8,}$/
          ),
        ],
      ],
      repassword: [
        '',
        [
          Validators.required,
          Validators.pattern(
            /^(?=.*[A-Za-z])(?=.*[@$!%*#?&_])[A-Za-z\d@$!%*#?&_]{8,}$/
          ),
        ],
      ],
    },
    {
      validator: ComparePassword('password', 'repassword'),
    }
  );

  get form() {
    return this.customer.controls;
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private countryService: CountryService,
    private router: Router,
    private toastController: ToastController
  ) {}

  ngOnInit() {
    this.getCountries();
  }

  async getCountries() {
    this.countryService.getCountries().subscribe(
      async (res) => {
        this.countries = res;
      },
      (err: any) => {
        if (err.error.message != null) {
          this.presentErrorToast(err.error.message);
        } else {
          this.presentErrorToast('Server not responding');
        }
      }
    );
  }

  getCities($event) {
    if (!this.form.country.value) {
      return;
    }
    this.countryService.getCities(this.form.country.value).subscribe(
      async (res) => {
        this.form.city.setValue(undefined);
        this.cities = res;
      },
      (err: any) => {
        if (err.error.message != null) {
          this.presentErrorToast(err.error.message);
        } else {
          this.presentErrorToast('Server not responding');
        }
      }
    );
  }

  async onSubmit() {
    this.submited = true;

    if (this.customer.invalid) {
      return;
    }

    console.log(this.customer);

    this.authService
      .register(this.customer.value)
      .pipe(take(1))
      .subscribe(
        async (res) => {
          console.log(res);

          localStorage.setItem('AUTH', JSON.stringify(res.data));
          this.authService.publishAuth(res.data);

          // this.customer.reset();

          this.presentSuccessToast();
          this.router.navigate(['/home']);
        },
        (err: any) => {
          if (err.error.message != null) {
            console.log(err);
            this.presentErrorToast(err.error.message);
          } else {
            this.presentErrorToast('Server not responding');
          }
        }
      );
  }

  async presentSuccessToast() {
    const toast = await this.toastController.create({
      message: 'Successful registration.',
      duration: 4000,
      color: 'success',
      buttons: [
        {
          text: 'Done',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          },
        },
      ],
    });
    toast.present();
  }

  async presentErrorToast(message: string) {
    const toast = await this.toastController.create({
      message: message,
      duration: 4000,
      color: 'blockbuster-text',
      buttons: [
        {
          text: 'Done',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          },
        },
      ],
    });
    toast.present();
  }

  showPassword() {
    if (this.passwordType === 'password') {
      this.passwordType = 'text';
    } else {
      this.passwordType = 'password';
    }
  }

  showRepassword() {
    if (this.repasswordType === 'password') {
      this.repasswordType = 'text';
    } else {
      this.repasswordType = 'password';
    }
  }
}
