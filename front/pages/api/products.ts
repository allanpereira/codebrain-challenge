import type { NextApiRequest, NextApiResponse } from 'next'
import { ProductsResponse } from "../../types/responses/ProductsResponse";

export default async (req: NextApiRequest, res: NextApiResponse<ProductsResponse>) => {
  const apiResponse = await fetch(`${process.env.API_HOST}/products`)
  const data = await apiResponse.json()

  res.status(200).json(data)
}