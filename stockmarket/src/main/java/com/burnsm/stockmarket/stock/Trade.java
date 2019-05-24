package com.burnsm.stockmarket.stock;

import java.time.LocalDateTime;

public class Trade {
	
	private LocalDateTime timestamp;
	private int quantity;
	private BuyOrSell buyOrSell;
	private double tradedPrice;
	
	public Trade(int quantity, BuyOrSell buyOrSell, double tradedPrice) {
		super();
		this.timestamp = LocalDateTime.now();
		this.quantity = quantity;
		this.buyOrSell = buyOrSell;
		this.tradedPrice = tradedPrice;
	}
	
	public Trade() {
		super();
		this.timestamp = LocalDateTime.now();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BuyOrSell getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(BuyOrSell buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public double getTradedPrice() {
		return tradedPrice;
	}

	public void setTradedPrice(double tradedPrice) {
		this.tradedPrice = tradedPrice;
	}
	
}
