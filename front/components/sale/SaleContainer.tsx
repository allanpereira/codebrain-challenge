import React from 'react';
import { Tabs, TabList, TabPanels, Tab, TabPanel } from '@chakra-ui/react'

import { SaleProvider } from "./SaleProvider";
import ShoppingCartTab from "./ShoppingCartTab";
import ProductsTab from "./ProductsTab";


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
            <ProductsTab/>
          </TabPanel>
          <TabPanel>
            <ShoppingCartTab/>
          </TabPanel>
        </TabPanels>
      </Tabs>
    </SaleProvider>
  );
}