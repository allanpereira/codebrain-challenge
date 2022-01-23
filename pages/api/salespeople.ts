import type { NextApiRequest, NextApiResponse } from 'next'
import { SalespeopleResponse } from "../../types/SalespeopleResponse";

export default async (req: NextApiRequest, res: NextApiResponse<SalespeopleResponse>) => {
  const apiResponse = await fetch(`${process.env.API_HOST}/salespeople`)
  const data = await apiResponse.json()

  res.status(200).json(data)
}