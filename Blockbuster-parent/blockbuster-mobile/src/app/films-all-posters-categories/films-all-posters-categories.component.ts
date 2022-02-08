import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Category } from '../_models/category';
import { CategoryService } from '../_services/category.service';

@Component({
  selector: 'app-films-all-posters-categories',
  templateUrl: './films-all-posters-categories.component.html',
  styleUrls: ['./films-all-posters-categories.component.css']
})
export class FilmsAllPostersCategoriesComponent implements OnInit {

  categories: Category[];



  constructor(private categoryService: CategoryService, private changeDetectorRef: ChangeDetectorRef) {

    this.categories = new Array<Category>();
  }



  ngOnInit() {
    this.categoryService.getAllCategories().subscribe(cat => {
      if (cat) {
        console.log("cat", cat);
        this.categories = cat.data;
        console.log("categories", this.categories);
      }

    });

  }

  getCategory(index) {
    if (this.categories && this.categories.length > 0) {
      return this.categories[index];
    }
    return null;
  }

}
