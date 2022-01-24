import React from "react";
import { Flex, Heading, Spacer } from "@chakra-ui/react";

import { useSale } from "../SaleProvider";
import CurrencyFormatter from "../../common/CurrencyFormatter";

export default function ShoppingCartTotalLabel() {
  const sale = useSale();

  return <>
    {sale.items.length > 0 &&
    (<>
      <hr/>
      <Flex spacing={8} align={"flex-end"} w={'100%'} mt={4} mb={4}>
        <Spacer />
        <Heading as='h4' size='md'>
          Total: {CurrencyFormatter(sale.totalPrice)}
        </Heading>
      </Flex>
      <hr/>
    </>)}
  </>
}