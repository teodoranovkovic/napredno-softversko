import { Component, OnInit, SecurityContext, ViewChild } from '@angular/core';

import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Auth } from 'src/app/_models/auth.model';
import { Profile } from 'src/app/_models/customer/profile.model';
import { AuthService } from 'src/app/_services/auth.service';
import { CustomerService } from 'src/app/_services/customer.service';
import { UploadImageService } from 'src/app/_services/upload-image.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.page.html',
  styleUrls: ['./edit-profile.page.scss'],
})
export class EditProfilePage implements OnInit {
  formProfile: FormGroup;
  profile: Profile;
  imageFileUrl: string;
  customerList: Profile[];
  nameList: String[];
  downloadURL: Observable<string>;

  @ViewChild('imageInput') imageInput;

  constructor(
    private route: ActivatedRoute,
    private customerService: CustomerService,
    private uploadImageService: UploadImageService,
    private sanitizer: DomSanitizer,
    private authService: AuthService
  ) {
    this.customerList = new Array<Profile>();
    this.nameList = new Array<String>();
  }

  ngOnInit() {
    this.subscribeOnImageUploadChanges();
    this.customerService
      .getClient(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((res) => {
        console.log(res.data);
        this.profile = res.data;
      });
    this.getAllCustomers();
    this.formProfile = new FormGroup({
      firstName: new FormControl(null, [
        Validators.required,
        Validators.pattern('[a-zA-Z][a-zA-Z ]{2,}'),
      ]),
      lastName: new FormControl(null, [
        Validators.required,
        Validators.pattern('[a-zA-Z][a-zA-Z ]{2,}'),
      ]),
      username: new FormControl(null, [
        Validators.required,
        this.forbiddenNames.bind(this),
        Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$'),
      ]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, [Validators.required]),
    });
  }

  chechPass() {
    if (this.customerService.errorMessage) {
      return true;
    }
    return null;
  }

  onSubmit() {
    this.profile.firstName = this.formProfile.get('firstName').value;
    this.profile.lastName = this.formProfile.get('lastName').value;
    this.profile.username = this.formProfile.get('username').value;
    this.profile.email = this.formProfile.get('email').value;
    this.profile.password = this.formProfile.get('password').value;

    if (this.imageFileUrl) {
      this.profile.avatarUrl = this.imageFileUrl;
    } else if (!this.imageFileUrl && !this.profile.avatarUrl) {
      this.profile.avatarUrl =
        'https://crowd-literature.eu/wp-content/uploads/2015/01/no-avatar.gif';
    }

    const auth: Auth = JSON.parse(localStorage.AUTH);
    auth.avatarUrl = this.profile.avatarUrl;
    this.authService.publishAuth(auth);
    localStorage.setItem('AUTH', JSON.stringify(auth));
    this.customerService.setClient(this.profile);
  }

  showPassword() {
    var x = document.getElementById('password') as HTMLInputElement;
    if (x.type === 'password') {
      x.type = 'text';
    } else {
      x.type = 'password';
    }
  }

  forbiddenNames(control: FormControl): { [s: string]: boolean } {
    if (this.customerList && this.customerList.length > 0) {
      this.customerList.forEach((element) => {
        if (element.username !== null) {
          this.nameList.push(element.username);
        }
      });
      console.log(
        'Name list' + this.nameList,
        this.nameList.indexOf(control.value)
      );
    }
    if (this.nameList.indexOf(control.value) !== -1)
      return { nameIsForbidden: true };

    return null;
  }

  getAllCustomers() {
    this.customerService.getAllCustomers().subscribe((allCustomers) => {
      this.customerList = allCustomers.data;
    });
  }

  imageInputChange() {
    const imageFile = this.imageInput.nativeElement.files[0];
    this.uploadImageService.uploadImageToFirebaseStorage(imageFile);
  }

  subscribeOnImageUploadChanges() {
    this.uploadImageService.imageUploadedSubject.subscribe((url) => {
      if (url) {
        this.imageFileUrl = this.sanitizer.sanitize(
          SecurityContext.URL,
          url.toString()
        );
        this.profile.avatarUrl = this.sanitizer.sanitize(
          SecurityContext.URL,
          url.toString()
        );
      }
    });
  }
}
