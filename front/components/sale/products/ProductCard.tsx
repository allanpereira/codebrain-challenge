import React, { useState } from 'react';
import { Box, Button, Image, Tooltip } from "@chakra-ui/react";

import NumberInputSelector from "../../common/NumberInputSelector";
import { Product } from "../../../types/entities/Product";
import CurrencyFormatter from "../../common/CurrencyFormatter";

type onAddToCartClickedType = (product: Product, quantity: number) => void

export default function ProductCard({ product, onAddToCartClicked }: { product: Product, onAddToCartClicked: onAddToCartClickedType }) {
  const [ quantity, setQuantity ] = useState(1);

  const onChangeQuantity = (value: number) => {
    console.log('setQuantity', value);

    setQuantity(value);
  }

  return (
    <Box maxW='sm' borderWidth='1px' borderRadius='lg' overflow='hidden'>
      <Image src={product.imageUrl} alt={product.name} objectFit='contain' w={'320px'} h={'320px'} p={'6'} />

      <Box p='6'>
        <Box
          mt='1'
          fontWeight='semibold'
          as='h4'
          lineHeight='tight'
          isTruncated
        >
          <Tooltip label={product.name}>
            {product.name}
          </Tooltip>
        </Box>

        <Box>
          {CurrencyFormatter(product.price)}
        </Box>

        <Box mt='4'>
          <NumberInputSelector min={1} max={100} onChange={onChangeQuantity}/>
        </Box>

        <Button variant='solid' w={'100%'} mt='4'
                backgroundColor={'#2B67E9'}
                color={'#FFF'}
                _hover={{ bg: '#2558c5'}}
                loadingText='Adding to cart'
                isLoading={false}
                onClick={() => onAddToCartClicked(product, quantity)}>
          Add to cart
        </Button>
      </Box>
    </Box>
  );
}