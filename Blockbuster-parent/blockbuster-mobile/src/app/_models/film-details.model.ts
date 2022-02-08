import { Actor } from './actor';

export interface FilmDetails {
  filmId: number;
  title: string;
  rating: string;
  length: number;
  category: string;
  language: string;
  starRating: number;
  posterUrl: string;
  description: string;
  actors: Actor[];
  rentalDuration: number;
  rentalRate: number;
  available: number;
  releaseYear: number;
}
