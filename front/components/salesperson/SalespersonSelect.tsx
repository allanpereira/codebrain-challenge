import React, { useState, useEffect } from 'react';
import {FormControl, FormLabel, Heading, Select} from '@chakra-ui/react'

import { Salesperson } from "../../types/entities/Salesperson";

export default function SalespersonSelect() {
  const [salespeople, setSalespeople] = useState<Salesperson[]>([]);
  const [value, setValue] = useState<string>('');

  useEffect(() => {
      async function loadSalespeople() {
        const response = await fetch('/api/salespeople');
        const { salespeople: salespeopleResponse } = await response.json();

        setSalespeople(salespeopleResponse);
      }

      loadSalespeople();
  }, [])

  return (
    <div>
      <FormControl>
        <Heading as='h4' size='md'>Salesperson</Heading>
        <Select
          mt={4}
          value={value}
          onChange={(e) => {
            setValue(e.target.value);
          }}
        >
          {
            salespeople.map(s => (<option value={s.id}>{s.name} ({s.registration})</option>))
          }
        </Select>
      </FormControl>
    </div>
  );
}