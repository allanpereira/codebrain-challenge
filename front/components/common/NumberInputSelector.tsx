import { Button, HStack, Input, useNumberInput } from "@chakra-ui/react";

export default function NumberInputSelector({ min, max, onChange }: { min: number, max: number, onChange: (event) => void }) {
  const { getInputProps, getIncrementButtonProps, getDecrementButtonProps } =
    useNumberInput({
      min,
      max,
      step: 1,
      defaultValue: 1,
      precision: 0,
      onChange: (valueAsString, valueAsNumber) => onChange(valueAsNumber),
    })

  const inc = getIncrementButtonProps()
  const dec = getDecrementButtonProps()
  const input = getInputProps({ readOnly: false })

  return (
    <HStack maxW='320px'>
      <Button {...dec}>-</Button>
      <Input {...input} />
      <Button {...inc}>+</Button>
    </HStack>
  )
}