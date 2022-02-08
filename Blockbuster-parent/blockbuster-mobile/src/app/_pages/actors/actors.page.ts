import { ViewWillEnter } from '@ionic/angular';
import { ActorService } from './../../_services/actor.service';
import { Component, OnInit } from '@angular/core';
import { Auth } from 'src/app/_models/auth.model';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-actors',
  templateUrl: './actors.page.html',
  styleUrls: ['./actors.page.scss'],
})
export class ActorsPage implements OnInit, ViewWillEnter {
  searchTerm = new String();
  showFavorites: boolean;

  auth: Auth;

  constructor(private actorService: ActorService, private authService: AuthService) { }

  ngOnInit() {
    this.authService
    .getAuthObservable()
    .subscribe((auth) => (this.auth = auth));
  }

  ionViewWillEnter() {
    const auth = localStorage.AUTH;
    if (auth) {
      this.auth = auth;
    }

    this.authService
      .getAuthObservable()
      .subscribe((auth) => (this.auth = auth));
  }

  onInputSearch(event) {
    this.searchTerm = event.target.value;
  }

  favouriteActorsChange() {
    this.actorService.publishFavourtieActors(this.showFavorites);
  }

  ionViewWillLeave() {
    this.searchTerm = "";
    this.showFavorites = false;
  }

}
