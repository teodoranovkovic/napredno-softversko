import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'descriptionCut'
})
export class DescriptionCutPipe implements PipeTransform {

  transform(description: string): string {
    return description.slice(0, 40) + "...";
  }



}
