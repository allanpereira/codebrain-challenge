import React, { useState, useEffect } from 'react';
import { FormControl, Heading, Select } from '@chakra-ui/react'

import { Salesperson } from "../../types/entities/Salesperson";

export default function SalespersonSelect({ onChange }) {
  const [salespeople, setSalespeople] = useState<Salesperson[]>([]);
  const [value, setValue] = useState<string>('');

  useEffect(() => {
      async function loadSalespeople() {
        const response = await fetch('/api/salespeople');
        const { salespeople: salespeopleResponse } = await response.json();

        setSalespeople(salespeopleResponse);

        if (salespeopleResponse.length > 0) {
          onChange(salespeopleResponse[0].id);
        }
      }

      loadSalespeople();
  }, [onChange])

  return (
    <div>
      <FormControl>
        <Heading as='h4' size='md'>Salesperson</Heading>
        <Select
          mt={4}
          value={value}
          onChange={(e) => {
            setValue(e.target.value);
            onChange(e.target.value);
          }}
        >
          {
            salespeople.map(s => (<option key={s.id} value={s.id}>{s.name} ({s.registration})</option>))
          }
        </Select>
      </FormControl>
    </div>
  );
}