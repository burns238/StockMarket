package com.burnsm.stockmarket.stock;

import java.util.List;

public class AllShareIndex {
	
	public static double nthRoot(double value, double n) {
        return Math.pow(Math.E, Math.log(value)/n);
    }
	
	public static double calculateAllShareIndex(List<Stock> stocks) {
		
		//In order to still produce a geometric mean when zero prices are present
		// (for example where one stock in the list has no trades in the last 15 mins)
		// we convert zeros to ones
		double productOfPrices = stocks.stream()
				.map(s -> getVolumeWeightedPriceAmendingZeros(s))
				.reduce(1.0, (subtotal, p) -> subtotal * p);
		
		if (stocks.size() != 0 && productOfPrices != 0) {
			return nthRoot(productOfPrices, stocks.size());
		} else {
			return 0;
		}
	}
	
	private static double getVolumeWeightedPriceAmendingZeros(Stock stock) {
		double price = stock.calculateVolumeWeightedStockPrice();
		if (price != 0) {
			return price;
		} else {
			return 1;
		}
	}
	
}
