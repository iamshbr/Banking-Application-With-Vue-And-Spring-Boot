
export default function useFormat() {

  const formatTransactionDate = function (date, locale) {
    const calcDaysPassed = (date1, date2) => Math.round(Math.abs(date2 - date1) / (1000 * 60 * 60 * 24));

    const daysPassed = calcDaysPassed(new Date(), new Date(date));
    if (daysPassed === 0) return "Today";
    if (daysPassed === 1) return "Yesterday";
    if (daysPassed <= 7) return `${daysPassed} days ago`;

    return new Intl.DateTimeFormat(locale).format(new Date(date));
  };

  const formatCurrency = function (amount, locale, currencyType) {
    if (currencyType && locale) {
      return new Intl.NumberFormat(locale, { style: "currency", currency: currencyType }).format(amount);
    }
    return amount;
  }



  return { formatTransactionDate, formatCurrency }

}

