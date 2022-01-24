import React, { useState, useEffect } from 'react';
import { SimpleGrid } from "@chakra-ui/react";

import ProductCard from "./ProductCard";
import { Product } from "../../../types/entities/Product";
import {useSaleDispatch, useSale} from "../SaleProvider";

export default function ProductsList() {
  const [products, setProducts] = useState<Product[]>([]);
  const dispatch = useSaleDispatch();

  const onAddToCartClicked = (product: Product, quantity: number) => {
    dispatch({
      type: 'ADD_ITEM_TO_SALE',
      payload: {
        product: product,
        quantity: quantity,
      }
    })
  }

  useEffect(() => {
    async function loadProducts() {
      const response = await fetch('/api/products');
      const { products: productsResponse } = await response.json();

      setProducts(productsResponse);
    }

    loadProducts();
  }, [])

  return (
    <SimpleGrid columns={[1, 2, 2, 3, 4]} spacing={6}>
      {
        products.map(product => {
          return (
            <ProductCard product={product} onAddToCartClicked={onAddToCartClicked} />
          )
        })
      }
    </SimpleGrid>
  );
}