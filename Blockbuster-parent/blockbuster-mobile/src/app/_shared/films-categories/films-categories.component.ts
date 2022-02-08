import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Category } from 'src/app/_models/category';
import { FilmForList } from 'src/app/_models/filmsForList';
import { CategoryService } from 'src/app/_services/category.service';

@Component({
  selector: 'app-films-categories',
  templateUrl: './films-categories.component.html',
  styleUrls: ['./films-categories.component.css']
})
export class FilmsCategoriesComponent implements OnInit {

  @Input('films')
  films: FilmForList[];

  @Input('filteredFilms')
  filteredFilms: FilmForList[];

  selectedCategory = new String();

  categories: Category[];

  @Output()//salje parentu discard ili niz
  categoryFilterFinishedSubject = new Subject();

  @Output()//ime kategorije za slider
  categoryChanged = new Subject();

  @Input('searchFilterEnabled')
  searchFilterEnabled = false;

  @Input('categoryFilterEnabled')
  categoryFilterEnabled = false;

  constructor(private categoryService: CategoryService) { }


  ngOnInit() {
    this.categories = new Array();
    const allCategory = new Category();
    allCategory.categoryId = -1;
    allCategory.name = 'All';
    this.categories.push(allCategory);
    this.categoryService.getAllCategories().subscribe(cat => {
      if (cat) {
        this.categories = this.categories.concat(cat.data);
      }

    });
    console.log("categories" + this.categories);

  }
  onCategoryChange($event) {
    this.selectedCategory = $event.target.value;
    if (this.filteredFilms && this.filteredFilms.length > 0 && this.categoryFilterEnabled && this.searchFilterEnabled) {
      this.categoryFilterFinishedSubject.next('discard');//postavljena su oba i imamo filter fi
    }
    else if (this.filteredFilms && this.filteredFilms.length > 0 && this.categoryFilterEnabled) {
      this.filterFilmsByCategory(this.films);
    } else if (this.filteredFilms && this.filteredFilms.length > 0) { // other filter component set filteredFilms
      this.filterFilmsByCategory(this.filteredFilms);
    }
    else {
      this.filterFilmsByCategory(this.films);
    }

    this.categoryChanged.next(this.selectedCategory);
  }

  filterFilmsByCategory(allFilms: FilmForList[]) {

    let filterFilmsCategory = new Array<FilmForList>();
    console.log("FIIILMS", allFilms);

    if (this.selectedCategory !== 'All') {
      allFilms.forEach(filmElement => {
        if (filmElement.categoryName?.toLowerCase().indexOf(this.selectedCategory.toLowerCase()) !== -1) {
          filterFilmsCategory.push(filmElement);
        }
      });
      this.categoryFilterFinishedSubject.next(filterFilmsCategory);
    }
    else {
      this.categoryFilterFinishedSubject.next('discard');
    }

  }

  customPopoverCategory: any = {
    header: 'Category',
    subHeader: 'Select category',
  };

  getSelectedCategory() {
    return this.selectedCategory;
  }
}
