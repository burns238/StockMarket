package com.burnsm.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.burnsm.stockmarket.stock.AllShareIndex;
import com.burnsm.stockmarket.stock.BuyOrSell;
import com.burnsm.stockmarket.stock.CommonStock;
import com.burnsm.stockmarket.stock.Stock;
import com.burnsm.stockmarket.stock.Trade;

public class AllShareIndexTests {

    private double DELTA = 0.0000001;

    @Test
    void nthRoot_4th81() {
        assertEquals(AllShareIndex.nthRoot(81.0, 4.0), 3, DELTA);
    }

    @Test
    void nthRoot_6th64() {
        assertEquals(AllShareIndex.nthRoot(64.0, 6.0), 2, DELTA);
    }

    @Test
    void nthRoot_square16() {
        assertEquals(AllShareIndex.nthRoot(16.0, 2.0), 4, DELTA);
    }

    @Test
    void nthRoot_4th4096() {
        assertEquals(AllShareIndex.nthRoot(4096.0, 4.0), 8, DELTA);
    }

    @Test
    void nthRoot_28th12345() {
        assertEquals(AllShareIndex.nthRoot(12345.0, 28.0), 1.3999891889, DELTA);
    }

    @Test
    void calculateAllShareIndex_oneStockNoTrades() {
        CommonStock stock = new CommonStock("TST", 20, 12);
        List<Stock> stocks = List.of(stock);
        assertEquals(AllShareIndex.calculateAllShareIndex(stocks), 1);
    }

    @Test
    void calculateAllShareIndex_oneStockOneTradesZeroPrice() {
        CommonStock stock = new CommonStock("TST", 20, 12);
        stock.recordTrade(new Trade(10, BuyOrSell.BUY, 0));
        List<Stock> stocks = List.of(stock);
        assertEquals(AllShareIndex.calculateAllShareIndex(stocks), 1);
    }

    @Test
    void calculateAllShareIndex_oneStockOneTradesNonZeroPrice() {
        CommonStock stock = new CommonStock("TST", 20, 12);
        stock.recordTrade(new Trade(10, BuyOrSell.BUY, 3));
        List<Stock> stocks = List.of(stock);
        assertEquals(AllShareIndex.calculateAllShareIndex(stocks), 3);
    }

    @Test
    void calculateAllShareIndex_twoStocksSamePrice() {
        CommonStock stock1 = new CommonStock("TST", 20, 12);
        stock1.recordTrade(new Trade(10, BuyOrSell.BUY, 3));
        CommonStock stock2 = new CommonStock("TS2", 20, 12);
        stock2.recordTrade(new Trade(10, BuyOrSell.BUY, 3));
        List<Stock> stocks = List.of(stock1, stock2);
        assertEquals(AllShareIndex.calculateAllShareIndex(stocks), 3);
    }

    @Test
    void calculateAllShareIndex_twoStocksVaryingPrices() {
        CommonStock stock1 = new CommonStock("TST", 20, 12);
        stock1.recordTrade(new Trade(10, BuyOrSell.BUY, 5));
        CommonStock stock2 = new CommonStock("TS2", 20, 12);
        stock2.recordTrade(new Trade(10, BuyOrSell.BUY, 3));
        List<Stock> stocks = List.of(stock1, stock2);

        double expectedOutcome = AllShareIndex.nthRoot(15, 2);

        assertEquals(AllShareIndex.calculateAllShareIndex(stocks), expectedOutcome);
    }

    @Test
    void calculateAllShareIndex_fiveStocksVaryingPrices() {
        CommonStock stock1 = new CommonStock("TST", 20, 12);
        stock1.recordTrade(new Trade(10, BuyOrSell.BUY, 5));
        CommonStock stock2 = new CommonStock("TS2", 20, 12);
        stock2.recordTrade(new Trade(10, BuyOrSell.BUY, 4));
        CommonStock stock3 = new CommonStock("TST", 20, 12);
        stock3.recordTrade(new Trade(10, BuyOrSell.BUY, 3));
        CommonStock stock4 = new CommonStock("TS2", 20, 12);
        stock4.recordTrade(new Trade(10, BuyOrSell.BUY, 2));
        CommonStock stock5 = new CommonStock("TST", 20, 12);
        stock5.recordTrade(new Trade(10, BuyOrSell.BUY, 1));
        List<Stock> stocks = List.of(stock1, stock2, stock3, stock4, stock5);

        double expectedOutcome = AllShareIndex.nthRoot((5 * 4 * 3 * 2 * 1), 5);

        assertEquals(AllShareIndex.calculateAllShareIndex(stocks), expectedOutcome);
    }

}
