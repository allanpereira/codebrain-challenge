import React, { useState, useEffect, useCallback } from 'react';
import {
  Box,
  FormControl,
  SimpleGrid,
  Text,
  Input,
  Button,
  NumberInputField,
  NumberInput,
  Center,
  useToast
} from "@chakra-ui/react";

import ProductCard from "./ProductCard";
import { Product } from "../../../types/entities/Product";
import { useSaleDispatch } from "../SaleProvider";

export default function ProductsList() {
  const [loadingProducts, setLoadingProducts] = useState<boolean>(false);

  const [idFilter, setIdFilter] = useState<string>('');
  const [nameFilter, setNameFilter] = useState<string>('');

  const [products, setProducts] = useState<Product[]>([]);
  const dispatch = useSaleDispatch();

  const toast = useToast();

  const loadProducts = async () => {
    setLoadingProducts(true);

    try {
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
    } catch (err) {
      toast({
        title: 'Error',
        description: "Error while loading products",
        position: 'bottom-right',
        status: 'error',
        duration: 3000,
        isClosable: true,
      })
    } finally {
      setLoadingProducts(false);
    }
  }

  const onAddToCartClicked = (product: Product, quantity: number) => {
    dispatch({
      type: 'ADD_ITEM_TO_SALE',
      payload: {
        product: product,
        quantity: quantity,
      }
    })

    toast({
      title: 'Success',
      description: "Item added to cart!",
      position: 'bottom-right',
      status: 'success',
      duration: 3000,
      isClosable: true,
    })
  }

  const onSearchClicked = () => {
    loadProducts();
  }

  useEffect(() => {
    loadProducts();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  return (
    <>
      <SimpleGrid columns={[1, 2, 2, 3, 4]} spacing={6}>
        <Box mb={4}>
          <FormControl>
            <Text>ID</Text>
            <NumberInput
              isDisabled={loadingProducts}
              inputMode={"numeric"}
              min={0}
              mt={4}
              value={idFilter}
              onChange={(value) => {
                setIdFilter(value);
              }}
            >
              <NumberInputField />
            </NumberInput>
          </FormControl>
        </Box>

        <Box mb={4}>
          <FormControl>
            <Text>Name</Text>
            <Input
              isDisabled={loadingProducts}
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
                    loadingText='Search...'
                    isLoading={loadingProducts}
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
              <ProductCard key={product.id} product={product} onAddToCartClicked={onAddToCartClicked} />
            )
          })
        }
      </SimpleGrid>

      {products.length === 0 && <Center>
        <Text as='i'>No products found</Text>
      </Center>}
    </>
  );
}