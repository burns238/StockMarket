package com.burnsm.stockmarket.stock;

public class PreferredStock extends Stock {

    private double fixedDividendPercentage;

    public PreferredStock(String stockSymbol, double lastDividend, double parValue, double fixedDividendPercentage) {
        super(stockSymbol, lastDividend, parValue);
        this.setFixedDividendPercentage(fixedDividendPercentage);
    }

    public double getDividendValue() {
        return (this.getFixedDividendPercentage() / 100) * this.getParValue();
    }

    public double getFixedDividendPercentage() {
        return fixedDividendPercentage;
    }

    public void setFixedDividendPercentage(double fixedDividendPercentage) {
        this.fixedDividendPercentage = fixedDividendPercentage;
    }

}
