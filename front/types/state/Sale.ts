import { SaleItem } from "./SaleItem";

export interface Sale {
  salespersonId: number,
  totalPrice: number,
  items: SaleItem[]
}