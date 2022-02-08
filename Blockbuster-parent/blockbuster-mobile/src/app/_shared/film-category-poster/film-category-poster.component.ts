import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/_models/category';

@Component({
  selector: 'app-film-category-poster',
  templateUrl: './film-category-poster.component.html',
  styleUrls: ['./film-category-poster.component.css']
})
export class FilmCategoryPosterComponent implements OnInit {

  _category: Category;
  @Input('category')

  set category(value: Category) {
    if (value) {
      this._category = value;
    }
    else {
      this._category.name = "Nema";
    }
  }

  get category() {
    return this._category;
  }

  constructor(public route: Router) { }

  ngOnInit() {

  }

  onPosterClick() {
    this.route.navigate(['films-categories/', this.category.categoryId]);
  }

}
