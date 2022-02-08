import { Injectable } from '@angular/core';
import { AngularFireStorage } from '@angular/fire/storage';
import { Subject } from 'rxjs';
import { finalize } from 'rxjs/operators';

const STORAGE_PATH = "gs://blockbuster-6a209.appspot.com";

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {
  downloadURL: any;
  imageFirebaseUrl: any;
  imageUploadedSubject = new Subject();
  constructor(private storage: AngularFireStorage) { }

  uploadImageToFirebaseStorage(imageFile) {
    const avatarUuid = "/blockbusterAvatar" + Math.random();
    const task = this.storage.upload(avatarUuid, imageFile);
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = this.storage.refFromURL(STORAGE_PATH + avatarUuid).getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              this.imageFirebaseUrl = url;
              this.imageUploadedSubject.next(url);
            }
            console.log(this.imageFirebaseUrl);
          });
        })
      )
      .subscribe(url => {
        if (url) {
          //console.log(url);
        }
      });
  }

}
