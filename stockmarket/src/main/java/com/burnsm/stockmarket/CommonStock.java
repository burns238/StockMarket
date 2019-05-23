package com.burnsm.stockmarket;

public class CommonStock extends Stock {

	public CommonStock(String stockSymbol, double lastDividend, double parValue) {
		super(stockSymbol, lastDividend, parValue);
	}
	
	public double calculateDividendYield(double price) {
		if (price != 0) {
			return this.getLastDividend() / price;
		} else {
			return 0.0;
		}
	}
	
	public double calculatePER(double price) {
		if (this.getLastDividend() != 0) {
			return price / this.getLastDividend();
		} else {
			return 0.0;
		}
	}

}
