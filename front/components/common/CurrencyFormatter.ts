const formatter = Intl.NumberFormat('en-US', {
  style: 'currency',
  currency: 'USD'
})

export default (valueInCents) => {
  return formatter.format(valueInCents / 100);
};