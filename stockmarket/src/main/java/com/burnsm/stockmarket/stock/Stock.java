package com.burnsm.stockmarket.stock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Stock {
	
	private String stockSymbol;
	private double lastDividend;
	private double parValue;
	private List<Trade> trades;
	
	public Stock(String stockSymbol, double lastDividend, double parValue) {
		super();
		this.stockSymbol = stockSymbol;
		this.lastDividend = lastDividend;
		this.parValue = parValue;
		this.trades = List.of();
	}
	
	public abstract double getDividendValue();
	
	public double calculateDividendYield(double price) {
		if (price != 0) {
			return this.getDividendValue() / price;
		} else {
			return 0.0;
		}
	}
	
	public double calculatePER(double price) {
		if (this.getDividendValue() != 0) {
			return price / this.getDividendValue();
		} else {
			return 0.0;
		}
	}
	
	private List<Trade> getTradesFromTheLastFifteenMinutes() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime fifteenMinutesAgo = now.minusMinutes(15);
		Predicate<Trade> recentTrades = t -> t.getTimestamp().compareTo(fifteenMinutesAgo) >= 0;
		
		return this.getTrades().stream()
					.filter(recentTrades)
					.collect(Collectors.toList());
	}
	
	public double calculateVolumeWeightedStockPrice() {
		
		List<Trade> recentTrades = getTradesFromTheLastFifteenMinutes();
		
		double priceByQuantity = recentTrades.stream()
				.map(t -> t.getTradedPrice() * t.getQuantity())
				.reduce(0.0, (subtotal, v) -> subtotal + v);
		
		double quantity = recentTrades.stream()
				.mapToInt(t -> t.getQuantity()).sum();
		
		if (quantity != 0) {
			return priceByQuantity / quantity;
		} else {
			return 0.0;
		}
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}
	
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

    public void setTrades(List<Trade> newTrades) {  
		List<Trade> tradeList = new ArrayList<>();
		for (Trade t : newTrades) {
			tradeList.add(t);
		}
		this.trades = tradeList;
    }  
  
    public List<Trade> getTrades() {  
        List<Trade> tradeList = new ArrayList<>();  
        for (Trade t : this.trades) {  
        	tradeList.add(t);  
        }  
        return tradeList;  
    }  
    
	public void recordTrade(Trade trade) {
		List<Trade> tradeList = this.getTrades();
		tradeList.add(trade);
		this.setTrades(tradeList);			
	}
	
}
