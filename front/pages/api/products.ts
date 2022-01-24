import type { NextApiRequest, NextApiResponse } from 'next'
import { ProductsResponse } from "../../types/responses/ProductsResponse";

export default async (req: NextApiRequest, res: NextApiResponse<ProductsResponse>) => {
  let url = `${process.env.API_HOST}/products?`;

  if (req.query.id) {
    url += `id=${req.query.id}`;
  }

  if (req.query.name) {
    url += `name=${req.query.name}`;
  }

  const apiResponse = await fetch(url)
  const data = await apiResponse.json()

  res.status(200).json(data)
}