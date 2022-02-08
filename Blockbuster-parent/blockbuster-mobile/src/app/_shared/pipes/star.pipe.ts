import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'star',
})
export class StarPipe implements PipeTransform {
  transform(value: number): { full: number; half: number; empty: number } {
    const scaledRating = value / 2;
    let full = Math.floor(scaledRating);
    let half = 0;
    const dec = scaledRating - full;
    if (dec < 0.25) {
      half = 0;
    } else if (dec < 0.75) {
      half = 1;
    } else {
      full += 1;
      half = 0;
    }
    let empty = 5 - full - half;
    return { full, half, empty };
  }
}
