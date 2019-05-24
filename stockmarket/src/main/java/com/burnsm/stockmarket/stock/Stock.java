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
	
	public abstract double calculateDividendYield(double price);
	
	public abstract double calculatePER(double price);
	
	public List<Trade> getTradesFromTheLastFifteenMinutes() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime fifteenMinutesAgo = now.minusMinutes(15);
		Predicate<Trade> recentTrades = t -> t.getTimestamp().compareTo(fifteenMinutesAgo) >= 0;
		
		return this.getTrades().parallelStream()
					.filter(recentTrades)
					.collect(Collectors.toList());
	}
	
	public double calculateVolumeWeightedStockPrice() {
		
		List<Trade> trades = getTradesFromTheLastFifteenMinutes();
		
		double priceByQuantity = trades.parallelStream()
									.map(t -> t.getTradedPrice() * t.getQuantity())
									.reduce(0.0, (subtotal, v) -> subtotal + v);
		
		double quantity = trades.parallelStream()
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
    
	public void createTrade(Trade trade) {
		List<Trade> tradeList = this.getTrades();
		tradeList.add(trade);
		this.setTrades(tradeList);			
	}
	
}
