import React from 'react';
import {
  Box,
  Button,
  Flex,
  Spacer,
  Heading,
  Text,
  Tooltip,
  IconButton,
  Grid,
  GridItem, Center
} from "@chakra-ui/react";
import {CheckIcon, CloseIcon} from '@chakra-ui/icons';
import {useSaleDispatch, useSale} from "../SaleProvider";
import CurrencyFormatter from "../../common/CurrencyFormatter";
import { SaleItem } from "../../../types/state/SaleItem";

export default function ShoppingCartProductsList() {
  const sale = useSale();
  const dispatch = useSaleDispatch();

  const onRemoveItemFromCartClicked = (item: SaleItem, index: number) => {
    dispatch({
      type: 'REMOVE_ITEM_FROM_SALE',
      payload: {
        index,
      }
    })
  }

  return (
    <Box mt={"2"}>
      {
        sale.items.map((item, idx) => {
          return <Box rounded='md' bg='white' mt={2} mb={2}>
            <Grid templateColumns='repeat(12, 1fr)' spacing={10}>
              <GridItem colSpan={8} >
                <Heading as='h5' size='sm'>
                  {item.product.name}
                </Heading>
                <Text fontSize='md'>{item.quantity}x ${item.product.price / 100}</Text>
              </GridItem>
              <GridItem colSpan={2} >
                <Heading as='h4' size='md'>
                  {CurrencyFormatter(item.product.price * item.quantity)}
                </Heading>
              </GridItem>
              <GridItem colSpan={2} >
                <Flex>
                  <Spacer />
                  <Box>
                    <Tooltip label='Remove product'>
                      <IconButton backgroundColor={'#F00'} color={'#FFF'} _hover={{ bg: '#d30000'}} aria-label='Remove product' icon={<CloseIcon />}
                      onClick={() => onRemoveItemFromCartClicked(item, idx)}
                      />
                    </Tooltip>
                  </Box>
                </Flex>
              </GridItem>
            </Grid>
          </Box>
        })
      }

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

        <Flex spacing={8} align={"flex-end"} w={'100%'}>
          <Spacer />
          <Button leftIcon={<CheckIcon />} variant='solid' mt='4' size='md'
                  backgroundColor={'#2B67E9'}
                  color={'#FFF'}
                  _hover={{ bg: '#2558c5'}}
                  loadingText='Finishing sale'
                  isLoading={false}
                  onClick={() => {}}>
            Confirm Sale
          </Button>
        </Flex>
      </>)}

      {sale.items.length === 0 && <Center>
        <Text as='i'>Your shopping cart is empty!</Text>
      </Center>}

    </Box>
  );
}