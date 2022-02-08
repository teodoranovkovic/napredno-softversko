export interface RentalRequest {
  rentalRequestId: number;
  filmId: number;
  storeId: number;
  customerId: number;
  status: 'accepted' | 'rejected' | 'pending' | 'canceled';
  createDate: Date;
  lastUpdate: Date;
  title: string;
  posterUrl: string;
  rentalDuration: number;
  rentalRate: number;
}
