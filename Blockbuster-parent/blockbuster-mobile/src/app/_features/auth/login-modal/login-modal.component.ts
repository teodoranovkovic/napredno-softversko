import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { CookieService } from 'ngx-cookie-service';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ErrorResponse } from 'src/app/_models/http/error-responser.model';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.scss'],
})
export class LoginModalComponent implements OnInit {
  form: FormGroup;
  loading = false;
  error: string = undefined;

  validation_messages = {
    email: [
      { type: 'required', message: 'Email is required.' },
      {
        type: 'email',
        message: 'Field must be a valid email.',
      },
    ],
    password: [
      { type: 'required', message: 'Password is required.' },
      {
        type: 'minlength',
        message: 'Password must be at least 8 characters long',
      },
      {
        type: 'pattern',
        message:
          'Password must contain at least one letter, one number and one special character',
      },
    ],
  };

  constructor(
    private modalController: ModalController,
    private router: Router,
    private authService: AuthService,
    private cookieService: CookieService
  ) {}

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', {
        validators: [Validators.required, Validators.email],
        updateOn: 'blur',
      }),
      password: new FormControl('', {
        validators: [
          Validators.required,
          Validators.minLength(8),
          // Validators.pattern(
          //   '^(?=.*[A-Za-z])(?=.*d)(?=.*[@$!%*#?&])[A-Za-zd@$!%*#?&]$'
          // ),
        ],

        //updateOn: 'blur',
      }),
    });
  }

  dismiss() {
    this.modalController.dismiss();
  }

  get email() {
    return this.form.get('email');
  }

  get password() {
    return this.form.get('password');
  }

  async login() {
    this.loading = true;
    const email = this.form.get('email').value;
    const password = this.form.get('password').value;
    this.authService
      .login(email, password)
      .pipe(
        catchError((err: ErrorResponse) => {
          this.loading = false;
          this.error = err.error.message;
          return throwError(err.error.message);
        })
      )
      .subscribe(async (res) => {
        this.loading = false;
        localStorage.setItem('AUTH', JSON.stringify(res.data));
        this.authService.publishAuth(res.data);
        this.router.navigate(['home']);
        await this.modalController.dismiss();
      });
  }

  async navigateToForgotPassword() {
    await this.modalController.dismiss();
    this.router.navigate(['forgot-password']);
  }

  async navigateToRegister() {
    await this.modalController.dismiss();
    this.router.navigate(['auth/register']);
  }
}
