const formatter = Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD'
})

const CurrencyFormatter = (valueInCents) => {
  return formatter.format(valueInCents / 100);
}

export default CurrencyFormatter;