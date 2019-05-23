package com.burnsm.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockTests {
	
	private double DELTA = 0.0000001;
	private CommonStock testStock = new CommonStock("TRD", 20, 12);
	
	@BeforeEach
	public void resetTestStock() {
		testStock = new CommonStock("TRD", 20, 12); 
	}

    @Test
    void calculateVolumeWeightedStockPrice_noTrades() {
    	assertEquals(testStock.calculateVolumeWeightedStockPrice(), 0.0, DELTA);
    }
    
    @Test
    void setTrades_listShallowCopy() {
    	List<Trade> trades = new ArrayList<>();
    	trades.add(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	
    	CommonStock stock = new CommonStock("TRD", 20, 12);
    	stock.setTrades(trades);
    	
    	trades.add(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	assertEquals(stock.getTrades().size(), 1);
    	assertNotEquals(stock.getTrades().size(), trades.size());
    }
    
    @Test
    void calculateVolumeWeightedStockPrice_oneTrade() {
    	testStock.createTrade(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	assertEquals(testStock.calculateVolumeWeightedStockPrice(), 2.0, DELTA);
    }
    
    @Test
    void calculateVolumeWeightedStockPrice_twoTradesSameQuantitySamePrice() {
    	testStock.createTrade(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	testStock.createTrade(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	assertEquals(testStock.calculateVolumeWeightedStockPrice(), 2, DELTA);
    }
    
    @Test
    void calculateVolumeWeightedStockPrice_twoTradesSameQuantityVaryingPrice() {
    	testStock.createTrade(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	testStock.createTrade(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 1));
    	assertEquals(testStock.calculateVolumeWeightedStockPrice(), 1.5, DELTA);
    }
    
    @Test
    void calculateVolumeWeightedStockPrice_twoTradesVaryingQuantities() {
    	testStock.createTrade(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 2));
    	testStock.createTrade(new Trade(LocalDateTime.now(), 30, BuyOrSell.BUY, 1));
    	assertEquals(testStock.calculateVolumeWeightedStockPrice(), 1.25, DELTA);
    }
    
    @Test
    void calculateVolumeWeightedStockPrice_fiveTradesVaryingQuantities() {
    	List<Trade> newTrades = List.of(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 5.0),
    									new Trade(LocalDateTime.now(), 20, BuyOrSell.BUY, 4.0),
    									new Trade(LocalDateTime.now(), 30, BuyOrSell.BUY, 3.0),
    									new Trade(LocalDateTime.now(), 40, BuyOrSell.BUY, 2.0),
    									new Trade(LocalDateTime.now(), 50, BuyOrSell.BUY, 1.0));
    	testStock.setTrades(newTrades);
    	assertEquals(testStock.getTradesFromTheLastFifteenMinutes().size(), 5);
    	double expectedPrice = ((50.0) + (80.0) + (90.0) + (80.0) + (50.0))/(150.0);
    	assertEquals(testStock.calculateVolumeWeightedStockPrice(), expectedPrice, DELTA);
    }
    
    @Test
    void addTradesToSample() {
    	List<Trade> newTrades = List.of(new Trade(LocalDateTime.now(), 10, BuyOrSell.BUY, 5.0),
				new Trade(LocalDateTime.now(), 20, BuyOrSell.BUY, 4.0),
				new Trade(LocalDateTime.now(), 30, BuyOrSell.BUY, 3.0),
				new Trade(LocalDateTime.now(), 40, BuyOrSell.BUY, 2.0),
				new Trade(LocalDateTime.now(), 50, BuyOrSell.BUY, 1.0));
    	SampleStocks.TEA_COMMON_STOCK.setTrades(newTrades);
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.getTrades().size(),5);
    }
    
    
}
