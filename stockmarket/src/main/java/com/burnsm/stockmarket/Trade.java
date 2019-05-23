package com.burnsm.stockmarket;

import java.time.LocalDateTime;

public class Trade {
	
	private LocalDateTime timestamp;
	private int quantity;
	private BuyOrSell buyOrSell;
	private double tradedPrice;
	
	public Trade(LocalDateTime timestamp, int quantity, BuyOrSell buyOrSell, double tradedPrice) {
		super();
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.buyOrSell = buyOrSell;
		this.tradedPrice = tradedPrice;
	}
	
	public Trade(LocalDateTime timestamp, int quantity, String buyOrSell, double tradedPrice) {
		super();
		this.timestamp = timestamp;
		this.quantity = quantity;
		if (buyOrSell == "B") {
			this.buyOrSell = BuyOrSell.BUY;
		} else {
			this.buyOrSell = BuyOrSell.SELL;
		}
		this.tradedPrice = tradedPrice;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public BuyOrSell getBuyOrSell() {
		return buyOrSell;
	}
	
	public String getBuyOrSellAsString() {
		if (buyOrSell == BuyOrSell.BUY) {
			return "B";
		} else {
			return "S";
		}
	}
	
	public double getTradedPrice() {
		return tradedPrice;
	}
	
}
