import { Component, Input, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { take } from 'rxjs/operators';
import { AuthService } from 'src/app/_services/auth.service';
import { ComparePassword } from 'src/app/_shared/validators/repassword.directive';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss'],
})
export class ChangePasswordComponent implements OnInit {
  validation_messages = {
    password: [
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
    confirmPassword: [
      {
        type: 'required',
        message: 'This field is required',
      },
      {
        type: 'pattern',
        message:
          'Password must have 8 characters, 1 capital and 1 special character',
      },
      {
        type: 'notEquivalent',
        message: 'Password missmatch',
      },
    ],
  };

  @Input() resetCode: string;

  form: FormGroup;

  constructor(
    private toastController: ToastController,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.resetCode = this.route.snapshot.paramMap.get('id');
    this.form = new FormGroup(
      {
        password: new FormControl('', [
          Validators.required,
          Validators.pattern(
            /^(?=.*[A-Za-z])(?=.*[@$!%*#?&_])[A-Za-z\d@$!%*#?&_]{8,}$/
          ),
        ]),
        confirmPassword: new FormControl('', [
          Validators.required,
          Validators.pattern(
            /^(?=.*[A-Za-z])(?=.*[@$!%*#?&_])[A-Za-z\d@$!%*#?&_]{8,}$/
          ),
        ]),
      },
      {
        validators: [this.comparisonValidator()],
      }
    );
  }

  get password() {
    return this.form.get('password');
  }

  get confirmPassword() {
    return this.form.get('confirmPassword');
  }

  comparisonValidator(): ValidatorFn {
    return (group: FormGroup): ValidationErrors => {
      const password = group.controls['password'];
      const confirmPassword = group.controls['confirmPassword'];
      if (password.value !== confirmPassword.value) {
        confirmPassword.setErrors({ notEquivalent: true });
      } else {
        confirmPassword.setErrors(null);
      }
      return;
    };
  }

  async onSubmit() {
    if (this.form.valid) {
      this.authService
        .passwordReset(this.resetCode, this.form.get('password').value)
        .pipe(take(1))
        .subscribe(
          async (res) => {
            const toast = await this.toastController.create({
              message: 'Password changed successfully!',
              duration: 5000,
            });
            this.router.navigate(['home']);
            await toast.present();
          },
          async (err) => {
            const toast = await this.toastController.create({
              message: err.error.message,
              duration: 3000,
            });
            await toast.present();
          }
        );
    }
  }
}
