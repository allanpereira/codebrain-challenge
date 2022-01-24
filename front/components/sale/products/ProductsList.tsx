import React, { useState, useEffect } from 'react';
import {Box, FormControl, Heading, Select, SimpleGrid, Text, Input, Button} from "@chakra-ui/react";

import ProductCard from "./ProductCard";
import { Product } from "../../../types/entities/Product";
import {useSaleDispatch, useSale} from "../SaleProvider";

export default function ProductsList() {
  const [idFilter, setIdFilter] = useState<string>('');
  const [nameFilter, setNameFilter] = useState<string>('');

  const [products, setProducts] = useState<Product[]>([]);
  const dispatch = useSaleDispatch();

  const loadProducts = async () => {
    let url = '/api/products?';

    if (idFilter) {
      url += `id=${idFilter}`;
    }

    if (nameFilter) {
      url += `name=${nameFilter}`;
    }

    const response = await fetch(url);
    const { products: productsResponse } = await response.json();

    setProducts(productsResponse);
  }

  const onAddToCartClicked = (product: Product, quantity: number) => {
    dispatch({
      type: 'ADD_ITEM_TO_SALE',
      payload: {
        product: product,
        quantity: quantity,
      }
    })
  }

  const onSearchClicked = () => {
    loadProducts();
  }

  useEffect(() => {
    loadProducts();
  }, [])

  return (
    <>
      <SimpleGrid columns={[1, 2, 2, 3, 4]} spacing={6}>
        <Box mb={4}>
          <FormControl>
            <Text>ID</Text>
            <Input
              mt={4}
              value={idFilter}
              onChange={(e) => {
                setIdFilter(e.target.value);
              }}
            />
          </FormControl>
        </Box>

        <Box mb={4}>
          <FormControl>
            <Text>Name</Text>
            <Input
              mt={4}
              value={nameFilter}
              onChange={(e) => {
                setNameFilter(e.target.value);
              }}
            />
          </FormControl>
        </Box>

        <Box mb={4}>
          <FormControl>
            <Text>&nbsp;</Text>
            <Button variant='solid' w={'100%'} mt='4'
                    backgroundColor={'#2B67E9'}
                    color={'#FFF'}
                    _hover={{ bg: '#2558c5'}}
                    loadingText='Search'
                    isLoading={false}
                    onClick={() => onSearchClicked()}>
              Search
            </Button>
          </FormControl>
        </Box>
      </SimpleGrid>

      <hr/>

      <SimpleGrid mt={4} columns={[1, 2, 2, 3, 4]} spacing={6}>
        {
          products.map(product => {
            return (
              <ProductCard product={product} onAddToCartClicked={onAddToCartClicked} />
            )
          })
        }
      </SimpleGrid>
    </>
  );
}