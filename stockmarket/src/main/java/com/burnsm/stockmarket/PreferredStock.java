package com.burnsm.stockmarket;

public class PreferredStock extends Stock {
	
	private double fixedDividendPercentage;
	
	public PreferredStock(String stockSymbol, double lastDividend, double parValue, double fixedDividendPercentage) {
		super(stockSymbol, lastDividend, parValue);
		this.setFixedDividendPercentage(fixedDividendPercentage);
	}
	
	public double calculateDividendYield(double price) {
		if (price != 0) {
			return getDividendValue() / price;
		} else {
			return 0.0;
		}
	}
	
	public double calculatePER(double price) {
		if (this.getDividendValue() != 0) {
			return price / getDividendValue();
		} else {
			return 0.0;
		}
	}
	
	public double getDividendValue() {
		return (this.getFixedDividendPercentage()/100) * this.getParValue();
	}

	public double getFixedDividendPercentage() {
		return fixedDividendPercentage;
	}

	public void setFixedDividendPercentage(double fixedDividendPercentage) {
		this.fixedDividendPercentage = fixedDividendPercentage;
	}
	
}
