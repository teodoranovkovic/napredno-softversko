export interface Payment {
  paymentId: number;
  customerId: number;
  amount: number;
  paymentDate: Date;
  type: 'in' | 'out';
  title?: string;
  posterUrl?: string;
}
