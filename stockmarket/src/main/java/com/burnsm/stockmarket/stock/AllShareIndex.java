package com.burnsm.stockmarket.stock;

import java.util.List;

public class AllShareIndex {
	
	public static double nthRoot(double base, double n) {
        return Math.pow(Math.E, Math.log(base)/n);
    }
	
	public static double calculateAllShareIndex(List<Stock> stocks) {
		
		double productOfPrices = stocks.parallelStream()
									.map(s -> s.calculateVolumeWeightedStockPrice())
									.reduce(1.0, (subtotal, p) -> subtotal * p);
		if (stocks.size() != 0 && productOfPrices != 0) {
			return nthRoot(productOfPrices, stocks.size());
		} else {
			return 0;
		}
	}
	
}
