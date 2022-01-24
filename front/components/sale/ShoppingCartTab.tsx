import React from 'react';
import SalespersonSelect from "../salesperson/SalespersonSelect";
import ShoppingCartContainer from "./shopping-cart/ShoppingCartContainer";
import { useSaleDispatch } from "./SaleProvider";

export default function ShoppingCartTab() {
  const dispatch = useSaleDispatch();

  const onChangeSalesperson = (salespersonId: number) => {
    dispatch({
      type: 'SET_SALESPERSON',
      payload: {
        salespersonId,
      }
    })
  }

  return <>
    <SalespersonSelect onChange={onChangeSalesperson}/>
    <ShoppingCartContainer />
  </>;
}