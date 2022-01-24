import { Box, ChakraProvider, Container, Grid } from '@chakra-ui/react'

import Head from 'next/head'
import SaleContainer from "../components/sale/SaleContainer";

export default function Home() {
  return (
    <ChakraProvider>
      <Head>
        <title>CodeBrain Sales - Create a sale</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>


      <Grid w='100%' minH="100vh" backgroundColor={'#5582e7'}>
        <Container maxW='container.xl' backgroundColor='#FFF' borderRadius="8" mt={"8"} mb={"8"}>
          <SaleContainer/>
        </Container>
      </Grid>
    </ChakraProvider>
  )
}
