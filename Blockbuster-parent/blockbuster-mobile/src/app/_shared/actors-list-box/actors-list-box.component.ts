import { FavouritesService } from './../../_services/favourites.service';
import { ToastController } from '@ionic/angular';
import { Auth } from './../../_models/auth.model';
import { Router } from '@angular/router';
import { Actor } from './../../_models/actor';
import { ActorService } from './../../_services/actor.service';
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';

@Component({
  selector: 'app-actors-list-box',
  templateUrl: './actors-list-box.component.html',
  styleUrls: ['./actors-list-box.component.scss'],
})
export class ActorsListBoxComponent implements OnInit {

  allActors: Actor[] = [];
  actorsForSearch: Actor[] = [];
  actors: Actor[] = [];
  offset = 0;
  _searchTerm = new String();
  auth: Auth;
  srce: boolean = true;
  favoriteActors = new Map();
  loading: boolean = false;
  favourites: boolean = false;
  noFavActors: boolean = false;

  // @Input()
  // set auth(val) {
  //   this._auth = val;
  // }
  // get auth() {
  //  return this._auth; 
  // }

  @Input()
  set searchTerm(val) {
    this._searchTerm = val;
    if (!this.favourites) {
      this.offset = 0;
      this.actors = [];
      this.loadActors();
    }
    else {
      this.searchActors();
    }

  }
  get searchTerm() {
    return this._searchTerm;
  }

  constructor(
    private actorService: ActorService,
    private router: Router,
    private authService: AuthService,
    private toastController: ToastController,
    private favouritesService: FavouritesService
  ) { }

  ngOnInit() {

    const auth = localStorage.AUTH;
    if (auth) {
      this.auth = JSON.parse(auth);
    }

    console.log(this.auth)
    

    // if (auth) {
    //   this.auth = JSON.parse(auth);
    // }

    this.authService
      .getAuthObservable()
      .subscribe((auth) => (this.auth = auth));

    if (localStorage.getItem('FavoriteActors')) {
      this.favoriteActors = new Map(JSON.parse(localStorage.getItem('FavoriteActors')));
    }

    this.favouritesService.getFavourtieActorsObservable().subscribe(favoriteActors => {
      this.favoriteActors = favoriteActors;
    })

    this.actorService.getFavourtieActorsObservable().subscribe(favourites => {
      this.favourites = favourites;
      if (!this.favourites) {
        this.noFavActors = false;
        this.actors = [];
        this.offset = 0;
        this.loadActors();
      }
      else {
        this.filterFavourites();
      }

    })

  }

  loadActors(event?) {

    if (!this.favourites) {
      this.actorService.getActors(this.searchTerm, 20, this.offset)
        .subscribe(actors => {
          this.actors = this.actors.concat(actors);
        })

      if (event !== undefined) {
        event.target.complete();
      }
    }
    else{
      event.target.complete();
    }

  }

  filterFavourites() {

    const favActors = this.favoriteActors.get(this.auth.customerId);

    if (favActors !== undefined) {
      if (this.allActors.length === 0) {
        this.actorService.getAllActors()
          .subscribe(actors => {
            this.loading = true;
            this.allActors = actors;
            this.actors = this.allActors.filter(actor => {
              return favActors.includes(actor.actorId);
            });
            this.actorsForSearch = this.actors;
            this.loading = false;
          })
      }
      else {
        this.actors = this.allActors.filter(actor => {
          return favActors.includes(actor.actorId);
        });
        this.actorsForSearch = this.actors;
      }
    }
    else {
      this.actors = [];
      this.noFavActors = true;
    }




  }

  searchActors() {
    const searchTerm = this.searchTerm.split(" ");
    this.actors = this.actorsForSearch.filter(actor => {

      if (searchTerm.length == 1) {
        return actor.name.toLowerCase().includes(searchTerm[0].toLowerCase());
      }
      else {
        return actor.name.toLowerCase().includes(searchTerm[0].toLowerCase()) &&
          actor.name.toLowerCase().includes(searchTerm[1].toLowerCase());
      }
    })
  }

  loadMoreActors(event) {
    this.offset = this.offset + 20;
    this.loadActors(event);
  }

  navigate() {
    this.router.navigate(['/actor/5'])
  }

  addToFavorite(actor) {

    if (!this.auth) {
      const toast = this.toastController.create({
        message: 'Login to add actors to favorites',
        duration: 2500
      }).then((t) => t.present());
      return;
    }
    let actors: string[] = [];
    if (this.favoriteActors.size > 0) {
      actors = this.favoriteActors.get(this.auth.customerId);
      if (actors) {
        const index = actors.indexOf(actor.actorId);

        if (index === -1) {
          actors.push(actor.actorId);
        }
        else {
          actors.splice(index, 1);
        }
        this.favoriteActors.delete(this.auth.customerId);
      }
      else {
        actors = [];
        actors.push(actor.actorId);
      }
    }
    else {
      actors.push(actor.actorId);
    }

    if (actors.length > 0) {
      this.favoriteActors.set(this.auth.customerId, actors);
    }

    this.favouritesService.publishFavourtieActors(this.favoriteActors);
    localStorage.removeItem('FavoriteActors');
    localStorage.setItem('FavoriteActors', JSON.stringify([...this.favoriteActors]));

    if (this.favourites) {
      const favActors = this.favoriteActors.get(this.auth.customerId);
      if (favActors !== undefined) {
        this.actors = this.actors.filter(actor => {
          return favActors.includes(actor.actorId);
        });
      }
      else {
        this.noFavActors = true;
        this.actors = [];
      }
    }
  }

  checkHeart(actor): boolean {

    this.favouritesService.getFavourtieActorsObservable().subscribe(favoriteActors => {
      let actors = this.favoriteActors.get(this.auth.customerId);
      if (actors) {
        const index = actors.indexOf(actor.actorId);

        if (index === -1) {
          return false;
        }
        else {
          return true;
        }
      }
      else return false;
    })

    if (!this.auth) {
      return false;
    }
    if (this.favoriteActors != null) {
      let actors: string[] = [];
      actors = this.favoriteActors.get(this.auth.customerId);
      if (actors) {
        const index = actors.indexOf(actor.actorId);

        if (index === -1) {
          return false;
        }
        else {
          return true;
        }
      }
      else return false;
    }
    return false;
  }

  ionViewWillLeave() {
    this.searchTerm = "";
  }

}
