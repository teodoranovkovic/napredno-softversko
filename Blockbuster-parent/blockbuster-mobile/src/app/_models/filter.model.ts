export interface Filter {
  type: string;
  default: string;
  filters: { text: string; value: string }[];
}
