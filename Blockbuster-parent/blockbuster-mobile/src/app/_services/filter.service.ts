import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Filter } from '../_models/filter.model';

@Injectable({
  providedIn: 'root',
})
export class FilterService {
  private filterSubject = new Subject<{ type: string; filter: string }>();

  typeFilter: Filter = {
    default: 'all',
    type: 'type',
    filters: [
      {
        text: 'All',
        value: 'all',
      },
      {
        text: 'In',
        value: 'in',
      },
      {
        text: 'Out',
        value: 'out',
      },
    ],
  };

  ageFilter: Filter = {
    type: 'age',
    default: 'all',
    filters: [
      {
        text: 'All',
        value: 'all',
      },
      {
        text: 'Today',
        value: 'today',
      },
      {
        text: 'Week',
        value: 'week',
      },
      {
        text: 'Month',
        value: 'month',
      },
    ],
  };

  constructor() {}

  publishFilter(type: string, filter: string) {
    this.filterSubject.next({ type, filter });
  }

  getFilterObservable() {
    return this.filterSubject.asObservable();
  }
}
