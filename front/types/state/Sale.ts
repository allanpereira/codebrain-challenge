import { SaleItem } from "./SaleItem";

export interface Sale {
  totalPrice: number,
  items: SaleItem[]
}