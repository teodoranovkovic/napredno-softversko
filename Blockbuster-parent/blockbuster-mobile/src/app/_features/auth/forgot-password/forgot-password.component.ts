import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { throwError } from 'rxjs';
import { catchError, take } from 'rxjs/operators';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss'],
})
export class ForgotPasswordComponent implements OnInit {
  validation_messages = {
    email: [
      { type: 'required', message: 'Email is required.' },
      {
        type: 'email',
        message: 'Field must be a valid email.',
      },
    ],
    confirm: [
      {
        type: 'required',
        message: 'You must confirm password reset',
      },
    ],
  };

  form: FormGroup;
  loading = false;

  constructor(
    private toastController: ToastController,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.email, Validators.required]),
      confirm: new FormControl(false, [Validators.requiredTrue]),
    });
  }

  get email() {
    return this.form.get('email');
  }

  get confirm() {
    return this.form.get('confirm');
  }

  async onSubmit() {
    this.loading = true;
    if (this.form.valid) {
      const toaster = await this.toastController.create({
        duration: 5000,
        header: 'An email has been sent',
        message:
          'Follow the link in the email to complete your password reset request',
      });
      this.authService
        .requestPasswordReset(this.form.get('email').value)
        .pipe(take(1))
        .subscribe(
          (res) => {
            this.loading = false;
            toaster.present();
            this.router.navigate(['home']);
          },
          async (err) => {
            this.loading = false;
            const t = await this.toastController.create({
              duration: 4000,
              header: 'Error',
              message: err.error.message,
            });
            t.present();
          }
        );
    }
  }
}
