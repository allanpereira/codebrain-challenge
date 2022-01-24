import { useReducer, useContext, createContext } from 'react'
import produce from "immer"

import { Sale } from "../../types/state/Sale";

const defaultSaleValue = {
  items: [],
  totalPrice: 0
};

const SaleStateContext = createContext<Sale>(defaultSaleValue);

const SaleDispatchContext = createContext((action) => {});

const reducer = (state: Sale, action) => {
  const recalculateTotalPrice = (draft) => draft.items.reduce((acc, item) => acc + item.totalPrice, 0);

  switch (action.type) {
    case 'ADD_ITEM_TO_SALE':
      return produce(state, (draft) => {
        draft.items.push({
          product: action.payload.product,
          quantity: action.payload.quantity,
          totalPrice: action.payload.product.price * action.payload.quantity
        });

        draft.totalPrice = recalculateTotalPrice(draft)
      });
    case 'REMOVE_ITEM_FROM_SALE':
      return produce(state, (draft) => {
        draft.items = draft.items.filter((item, index) => index !== action.payload.index);
        draft.totalPrice = recalculateTotalPrice(draft)
      });
    default:
      throw new Error(`Unknown action: ${action.type}`)
  }
}

export const SaleProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, defaultSaleValue)
  return (
    <SaleDispatchContext.Provider value={dispatch}>
      <SaleStateContext.Provider value={state}>
        {children}
      </SaleStateContext.Provider>
    </SaleDispatchContext.Provider>
  )
}

export const useSale = () => useContext(SaleStateContext)
export const useSaleDispatch = () => useContext(SaleDispatchContext)