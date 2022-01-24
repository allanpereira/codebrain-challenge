import React, { createContext, useContext, useState, useEffect } from 'react';
import { Tabs, TabList, TabPanels, Tab, TabPanel } from '@chakra-ui/react'

import ProductsSelectionContainer from "./products/ProductsSelectionContainer";
import SalespersonSelect from "../salesperson/SalespersonSelect";
import ShoppingCartContainer from "./shopping-cart/ShoppingCartContainer";
import { SaleProvider } from "./SaleProvider";


export default function SaleContainer() {
  return (
    <SaleProvider>
      <Tabs>
        <TabList>
          <Tab>Products</Tab>
          <Tab>Shopping Cart</Tab>
        </TabList>

        <TabPanels>
          <TabPanel>
            <ProductsSelectionContainer />
          </TabPanel>
          <TabPanel>
            <SalespersonSelect />

            <ShoppingCartContainer />
          </TabPanel>
        </TabPanels>
      </Tabs>
    </SaleProvider>
  );
}