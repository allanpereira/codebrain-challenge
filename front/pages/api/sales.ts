import type { NextApiRequest, NextApiResponse } from 'next'

const salesApi = async (req: NextApiRequest, res: NextApiResponse) => {
  const apiResponse = await fetch(`${process.env.API_HOST}/sales`, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: req.body
  });

  const data = await apiResponse.json()
  res.status(200).json(data)
}

export default salesApi