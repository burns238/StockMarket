package com.burnsm.stockmarket.stock;

public class CommonStock extends Stock {

	public CommonStock(String stockSymbol, double lastDividend, double parValue) {
		super(stockSymbol, lastDividend, parValue);
	}
	
	public double getDividendValue() {
		return (this.getLastDividend());
	}

}
