import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ViewDidLeave, ViewWillEnter, ViewWillLeave } from '@ionic/angular';
import { Auth } from 'src/app/_models/auth.model';
import { FilmDetails } from 'src/app/_models/film-details.model';
import { FilmService } from 'src/app/_services/film.service';

@Component({
  selector: 'app-film',
  templateUrl: './film.page.html',
  styleUrls: ['./film.page.scss'],
})
export class FilmPage implements OnInit, ViewWillEnter, ViewDidLeave {
  auth: Auth;
  film: FilmDetails;

  constructor(
    private filmService: FilmService,
    private router: ActivatedRoute
  ) {}
  ionViewDidLeave(): void {
    this.film = undefined;
  }

  ngOnInit() {}

  ionViewWillEnter() {
    const auth = localStorage.AUTH;
    if (auth) {
      this.auth = JSON.parse(auth);
    }
    this.filmService
      .getFilmDetails(
        Number(this.router.snapshot.paramMap.get('id')),
        this.auth ? this.auth.storeId : 0
      )
      .subscribe((res) => {
        this.film = res.data;
      });
  }
}
