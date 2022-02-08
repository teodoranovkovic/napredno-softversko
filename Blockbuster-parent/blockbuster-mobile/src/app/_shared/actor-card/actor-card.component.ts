import { FavouritesService } from './../../_services/favourites.service';
import { ToastController } from '@ionic/angular';
import { Auth } from './../../_models/auth.model';
import { DarkModeService } from './../../_services/dark-mode.service';
import { ActivatedRoute } from '@angular/router';
import { ActorService } from './../../_services/actor.service';
import { Actor } from './../../_models/actor';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-actor-card',
  templateUrl: './actor-card.component.html',
  styleUrls: ['./actor-card.component.scss'],
})
export class ActorCardComponent implements OnInit {
  actor: Actor = {
    actorId: 0,
    name: '',
    pictureUrl: '',
    biography: '',
  };

  favoriteActors = new Map();
  auth: Auth = undefined;
  heart: boolean;
  actorName: string = '';
  heartColor: string = '';

  constructor(
    private actorService: ActorService,
    private route: ActivatedRoute,
    private darkModeService: DarkModeService,
    private authService: AuthService,
    private toastController: ToastController,
    private favouritesService: FavouritesService
  ) {}
  ngOnInit() {
    const dark = localStorage.getItem('DARK');
    if (dark) {
      this.heartColor = 'blockbuster-text';
    }

    this.darkModeService.getAuthObservable().subscribe((dark) => {
      if (dark) {
        this.heartColor = 'blockbuster-text';
      } else {
        this.heartColor = 'blockbuster-bg';
      }
    });

    this.loadActor();

    const auth = localStorage.AUTH;

    if (auth) {
      this.auth = JSON.parse(auth);
    }

    this.authService
      .getAuthObservable()
      .subscribe((auth) => (this.auth = auth));

    if (localStorage.getItem('FavoriteActors')) {
      this.favoriteActors = new Map(
        JSON.parse(localStorage.getItem('FavoriteActors'))
      );
    }

    
  }

  loadActor() {
    this.actorService
      .getActor(Number(this.route.snapshot.paramMap.get('id')))
      .subscribe((actor: Actor) => {
        this.actor = actor;
        this.actor.biography = this.actor.biography.slice(0, 250) + '...';
      });
  }

  addToFavorite() {
    if (!this.auth) {
      const toast = this.toastController
        .create({
          message: 'Login to add actors to favorites',
          duration: 2500,
        })
        .then((t) => t.present());
      return;
    }
    let actors: any[] = [];
    if (this.favoriteActors.size > 0) {
      actors = this.favoriteActors.get(this.auth.customerId);
      if (actors) {
        const index = actors.indexOf(this.actor.actorId);

        if (index === -1) {
          actors.push(this.actor.actorId);
        } else {
          actors.splice(index, 1);
        }
        this.favoriteActors.delete(this.auth.customerId);
      } else {
        actors = [];
        actors.push(this.actor.actorId);
      }
    } else {
      actors.push(this.actor.actorId);
    }

    if (actors.length > 0) {
      this.favoriteActors.set(this.auth.customerId, actors);
    }

    localStorage.removeItem('FavoriteActors');
    localStorage.setItem(
      'FavoriteActors',
      JSON.stringify([...this.favoriteActors])
    );
    this.favouritesService.publishFavourtieActors(this.favoriteActors);
    this.heart = this.checkHeart();
  }

  checkHeart(): boolean {
    if (!this.auth) {
      return false;
    }
    if (this.favoriteActors != null) {
      let actors: any[] = [];
      actors = this.favoriteActors.get(this.auth.customerId);
      if (actors) {
        const index = actors.indexOf(this.actor.actorId);

        if (index === -1) {
          return false;
        } else {
          return true;
        }
      } else return false;
    }
    return false;
  }
}
