# StockMarket

A super simple stock market implementation allowing users to record trades and determine dividend yields, 
PE Ratios, and volume weighted prices for given sample stocks.

## REST Endpoints

Once the application is running, the following endpoints are accessible from localhost port 8080.

### Get Stocks - /stock
Retrieves all of the sample stocks.

### Get Stock - /stock/{id}/
Retrieves a given sample stock by its stock symbol (TEA, POP, ALE, GIN, JOE).

### Calculate Dividend Yield - /stock/{id}/dividendyield/{price}/
Calculates the dividend yield for a given sample stock and a given price.

For Common stocks this is simply `Last Dividend / Price`

For Preferred stocks this is `(Fixed Dividend Percentage . Par Value) / Price`

### Calculate PERatio - /stock/{id}/peratio/{price}/
Calculates the Price-to-Earnings ratio for a given sample stock and a given price.

For Common stocks this is `Price / Last Dividend`

For Preferred stocks this is `Price / (Fixed Dividend Percentage . Par Value)`

### Record Trade - /stock/{id}/trade/
Record a trade for a given sample stock.

This transaction creates a trade under the given stock with the current timestamp, and a quantity, traded price, and 
buy or sell flag as entered in the body of the request.

An example body:
```
{
	"quantity":120,
	"buyOrSell":"BUY",
	"tradedPrice":2
}
```

### Calculate Volume Weighted Stock Price - /stock/{id}/volumeweightedstockprice/
Calculates the volume weighted stock price for the given stock based on trades in the past 15 minutes.

This is calculated as the sum of price x quantity for each trade divided by 
the sum of all trade quantities for the given stock.

### Calculate All Share Index - /stock/allshareindex/
Calculates the GBCE All Share Index using the geometric mean of volume weighted stock prices for all stocks.


