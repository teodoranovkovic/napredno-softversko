export interface Auth {
  customerId: number;
  email: string;
  username: string;
  avatarUrl: string;
  balance: number;
  firstName: string;
  lastName: string;
  storeId: number;
  lat?: number;
  lng?: number;
}
