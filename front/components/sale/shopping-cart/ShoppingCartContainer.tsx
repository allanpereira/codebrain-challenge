import React from 'react';
import {Box, FormControl, Heading } from "@chakra-ui/react";
import ShoppingCartProductsList from "./ShoppingCartProductsList";

export default function ShoppingCartContainer() {
  return (
    <Box mt={"8"}>
      <FormControl>
        <Heading as='h4' size='md'>Items</Heading>
      </FormControl>

      <ShoppingCartProductsList />
    </Box>
  );
}