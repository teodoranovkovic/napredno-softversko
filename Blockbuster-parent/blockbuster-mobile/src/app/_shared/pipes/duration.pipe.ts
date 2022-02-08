import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'duration',
})
export class DurationPipe implements PipeTransform {
  transform(duration: number): string {
    const hours = duration / 60;
    const rHours = Math.floor(hours);
    const minutes = (hours - rHours) * 60;
    var rMinutes = Math.round(minutes);
    return rHours > 0 ? `${rHours}h ${rMinutes}min` : `${rMinutes}min`;
  }
}
