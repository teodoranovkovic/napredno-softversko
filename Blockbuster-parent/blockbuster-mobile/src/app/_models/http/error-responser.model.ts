import { Response } from './response.model';

export interface ErrorResponse {
  error: Response<undefined>;
  message: string;
}
