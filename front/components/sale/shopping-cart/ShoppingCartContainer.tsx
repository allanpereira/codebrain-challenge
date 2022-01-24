import React, {useState} from 'react';
import { Box, Button, Flex, FormControl, Heading, Spacer } from "@chakra-ui/react";
import {CheckIcon } from "@chakra-ui/icons";

import ShoppingCartProductsList from "./ShoppingCartProductsList";
import ShoppingCartTotalLabel from "./ShoppingCartTotalLabel";
import { useSale, useSaleDispatch } from "../SaleProvider";

export default function ShoppingCartContainer() {
  const [submittingSale, setSubmittingSale] = useState(false);

  const sale = useSale();
  const dispatch = useSaleDispatch();

  const onConfirmSaleClicked = async () => {
    setSubmittingSale(true);

    try {
      const request = {
        salespersonId: sale.salespersonId,
        products: sale.items.map(i => ({
          id: i.product.id,
          quantity: i.quantity
        }))
      }

      await fetch('/api/sales', {
        method: 'POST',
        body: JSON.stringify(request),
      })

      dispatch({
        type: 'CLEAR_SALE'
      })
    } finally {
      setSubmittingSale(false);
    }
  }

  return (
    <Box mt={"8"}>
      <FormControl>
        <Heading as='h4' size='md'>Items</Heading>
      </FormControl>

      <ShoppingCartProductsList />

      {sale.items.length > 0 &&
      (<>
        <ShoppingCartTotalLabel />

        <Flex spacing={8} align={"flex-end"} w={'100%'}>
          <Spacer />
          <Button leftIcon={<CheckIcon />} variant='solid' mt='4' size='md'
                  backgroundColor={'#2B67E9'}
                  color={'#FFF'}
                  _hover={{ bg: '#2558c5'}}
                  loadingText='Finishing sale...'
                  isLoading={submittingSale}
                  onClick={() => onConfirmSaleClicked()}>
            Confirm Sale
          </Button>
        </Flex>
      </>)}

    </Box>
  );
}