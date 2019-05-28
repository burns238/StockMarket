package com.burnsm.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.burnsm.stockmarket.stock.BuyOrSell;
import com.burnsm.stockmarket.stock.CommonStock;
import com.burnsm.stockmarket.stock.Trade;

class StockTests {

    private double DELTA = 0.0000001;
    private CommonStock testStock = new CommonStock("TRD", 20, 12);

    @BeforeEach
    public void resetTestStock() {
        testStock = new CommonStock("TRD", 20, 12);
    }

    @Test
    void setTrades_listShallowCopy() {
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade(10, BuyOrSell.BUY, 2));

        CommonStock stock = new CommonStock("TRD", 20, 12);
        stock.setTrades(trades);

        trades.add(new Trade(10, BuyOrSell.BUY, 2));
        assertEquals(stock.getTrades().size(), 1);
        assertNotEquals(stock.getTrades().size(), trades.size());
    }

    @Test
    void calculateVolumeWeightedStockPrice_noTrades() {
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), 0.0, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_oneTrade() {
        testStock.recordTrade(new Trade(10, BuyOrSell.BUY, 2));
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), 2.0, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_twoTradesSameQuantitySamePrice() {
        testStock.recordTrade(new Trade(10, BuyOrSell.BUY, 2));
        testStock.recordTrade(new Trade(10, BuyOrSell.BUY, 2));
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), 2, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_twoTradesSameQuantityVaryingPrice() {
        testStock.recordTrade(new Trade(10, BuyOrSell.BUY, 2));
        testStock.recordTrade(new Trade(10, BuyOrSell.BUY, 1));
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), 1.5, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_twoTradesVaryingQuantities() {
        testStock.recordTrade(new Trade(10, BuyOrSell.BUY, 2));
        testStock.recordTrade(new Trade(30, BuyOrSell.BUY, 1));
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), 1.25, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_fiveTradesVaryingQuantities() {
        List<Trade> newTrades = List.of(new Trade(10, BuyOrSell.BUY, 5.0), new Trade(20, BuyOrSell.BUY, 4.0),
                new Trade(30, BuyOrSell.BUY, 3.0), new Trade(40, BuyOrSell.BUY, 2.0),
                new Trade(50, BuyOrSell.BUY, 1.0));
        testStock.setTrades(newTrades);
        double expectedPrice = ((50.0) + (80.0) + (90.0) + (80.0) + (50.0)) / (150.0);
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), expectedPrice, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_mixOldTradesRecentTrades() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyMinutesAgo = now.minusMinutes(30);

        Trade oldTrade1 = new Trade(10, BuyOrSell.BUY, 5.0);
        oldTrade1.setTimestamp(thirtyMinutesAgo);
        Trade oldTrade2 = new Trade(20, BuyOrSell.BUY, 4.0);
        oldTrade2.setTimestamp(thirtyMinutesAgo);
        Trade recentTrade1 = new Trade(30, BuyOrSell.BUY, 3.0);
        Trade recentTrade2 = new Trade(40, BuyOrSell.BUY, 2.0);
        Trade recentTrade3 = new Trade(50, BuyOrSell.BUY, 1.0);
        List<Trade> newTrades = List.of(oldTrade1, oldTrade2, recentTrade1, recentTrade2, recentTrade3);
        testStock.setTrades(newTrades);

        double expectedPrice = ((90.0) + (80.0) + (50.0)) / (120.0);
        assertEquals(testStock.calculateVolumeWeightedStockPrice(), expectedPrice, DELTA);
    }

    @Test
    void calculateVolumeWeightedStockPrice_onlyOldTrades() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyMinutesAgo = now.minusMinutes(30);

        Trade oldTrade1 = new Trade(10, BuyOrSell.BUY, 5.0);
        oldTrade1.setTimestamp(thirtyMinutesAgo);
        Trade oldTrade2 = new Trade(20, BuyOrSell.BUY, 4.0);
        oldTrade2.setTimestamp(thirtyMinutesAgo);
        Trade oldTrade3 = new Trade(30, BuyOrSell.BUY, 3.0);
        oldTrade3.setTimestamp(thirtyMinutesAgo);
        Trade oldTrade4 = new Trade(40, BuyOrSell.BUY, 2.0);
        oldTrade4.setTimestamp(thirtyMinutesAgo);
        Trade oldTrade5 = new Trade(50, BuyOrSell.BUY, 1.0);
        oldTrade5.setTimestamp(thirtyMinutesAgo);
        List<Trade> newTrades = List.of(oldTrade1, oldTrade2, oldTrade3, oldTrade4, oldTrade5);
        testStock.setTrades(newTrades);

        assertEquals(testStock.calculateVolumeWeightedStockPrice(), 0, DELTA);
    }

}
