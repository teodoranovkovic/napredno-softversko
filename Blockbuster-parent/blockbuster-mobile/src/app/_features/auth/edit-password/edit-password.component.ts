import { Component, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { take } from 'rxjs/operators';
import { Auth } from 'src/app/_models/auth.model';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-edit-password',
  templateUrl: './edit-password.component.html',
  styleUrls: ['./edit-password.component.scss'],
})
export class EditPasswordComponent implements OnInit {
  validation_messages = {
    oldPassword: [
      {
        type: 'required',
        message: 'This field is required',
      },
      {
        type: 'pattern',
        message:
          'Password must have 8 characters, 1 capital and 1 special character',
      },
    ],
    newPassword: [
      {
        type: 'required',
        message: 'This field is required',
      },
      {
        type: 'pattern',
        message:
          'Password must have 8 characters, 1 capital and 1 special character',
      },
    ],
    confirmNewPassword: [
      {
        type: 'required',
        message: 'This field is required',
      },
      {
        type: 'notEquivalent',
        message: 'Password missmatch',
      },
    ],
  };

  form: FormGroup;

  constructor(
    private authService: AuthService,
    private toastController: ToastController,
    private router: Router
  ) {}

  ngOnInit() {
    this.form = new FormGroup(
      {
        oldPassword: new FormControl('', [
          Validators.required,
          Validators.pattern(
            /^(?=.*[A-Za-z])(?=.*[@$!%*#?&_])[A-Za-z\d@$!%*#?&_]{8,}$/
          ),
        ]),
        newPassword: new FormControl('', [
          Validators.required,
          Validators.pattern(
            /^(?=.*[A-Za-z])(?=.*[@$!%*#?&_])[A-Za-z\d@$!%*#?&_]{8,}$/
          ),
        ]),
        confirmNewPassword: new FormControl('', [Validators.required]),
      },
      { validators: [this.comparisonValidator()] }
    );
  }

  get oldPassword() {
    return this.form.get('oldPassword');
  }

  get newPassword() {
    return this.form.get('newPassword');
  }

  get confirmNewPassword() {
    return this.form.get('confirmNewPassword');
  }

  comparisonValidator(): ValidatorFn {
    return (group: FormGroup): ValidationErrors => {
      const password = group.controls['newPassword'];
      const confirmPassword = group.controls['confirmNewPassword'];
      if (password.value !== confirmPassword.value) {
        confirmPassword.setErrors({ notEquivalent: true });
      } else {
        confirmPassword.setErrors(null);
      }
      return;
    };
  }

  onSubmit() {
    if (this.form.valid) {
      let auth: Auth;
      if (localStorage.AUTH) {
        auth = JSON.parse(localStorage.AUTH);
      }
      this.authService
        .editPassword(
          auth.customerId,
          this.oldPassword.value,
          this.newPassword.value
        )
        .pipe(take(1))
        .subscribe(
          async (res) => {
            const toast = await this.toastController.create({
              message: 'Password changed successfully!',
              duration: 3000,
            });
            toast.present();
            this.router.navigate(['profile']);
          },
          async (err) => {
            const toast = await this.toastController.create({
              message: err.error.message,
              duration: 3000,
            });
            toast.present();
          }
        );
    }
  }
}
