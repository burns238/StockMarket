package com.burnsm.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.burnsm.stockmarket.stock.PreferredStock;
import com.burnsm.stockmarket.stock.SampleStocks;

class PreferredStockTests {

    private double LARGE_PRICE = 12345;
    private double DELTA = 0.0000001;
    private PreferredStock ZERO_FIXED_DIVIDEND_PREFERRED_STOCK = new PreferredStock("ZER", 10, 0, 2);
    private PreferredStock LARGE_FIXED_DIVIDEND_PREFERRED_STOCK = new PreferredStock("LAR", 20, 5000, 50);

    @Test
    void calculateDividendYield_zeroPriceNonZeroFixedDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculateDividendYield(0), 0.0, DELTA);
    }

    @Test
    void calculateDividendYield_zeroPriceZeroFixedDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(0), 0.0, DELTA);
    }

    @Test
    void calculateDividendYield_zeroPriceLargeFixedDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(0), 0.0, DELTA);
    }

    @Test
    void calculateDividendYield_OnePriceNonZeroFixedDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculateDividendYield(1), 2, DELTA);
    }

    @Test
    void calculateDividendYield_OnePriceZeroFixedDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(1), 0, DELTA);
    }

    @Test
    void calculateDividendYield_OnePriceLargeFixedDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(1), 2500, DELTA);
    }

    @Test
    void calculateDividendYield_TwoPriceNonZeroFixedDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculateDividendYield(2), 1, DELTA);
    }

    @Test
    void calculateDividendYield_TwoPriceZeroFixedDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(2), 0, DELTA);
    }

    @Test
    void calculateDividendYield_TwoPriceLargeFixedDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(2), 1250, DELTA);
    }

    @Test
    void calculateDividendYield_LargePriceNonZeroFixedDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculateDividendYield(LARGE_PRICE), 2 / LARGE_PRICE, DELTA);
    }

    @Test
    void calculateDividendYield_LargePriceZeroFixedDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(LARGE_PRICE), 0, DELTA);
    }

    @Test
    void calculateDividendYield_LargePriceLargeFixedDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculateDividendYield(LARGE_PRICE), 2500 / LARGE_PRICE,
                DELTA);
    }

    @Test
    void calculatePER_zeroPriceNonZeroDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculatePER(0), 0.0, DELTA);
    }

    @Test
    void calculatePER_zeroPriceZeroDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculatePER(0), 0.0, DELTA);
    }

    @Test
    void calculatePER_zeroPriceLargeDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculatePER(0), 0.0, DELTA);
    }

    @Test
    void calculatePER_onePriceNonZeroDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculatePER(1), 1.0 / 2.0, DELTA);
    }

    @Test
    void calculatePER_onePriceZeroDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculatePER(1), 0.0, DELTA);
    }

    @Test
    void calculatePER_onePriceLargeDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculatePER(1), 1.0 / 2500, DELTA);
    }

    @Test
    void calculatePER_largePriceNonZeroDividend() {
        assertEquals(SampleStocks.ginPreferredStock.calculatePER(LARGE_PRICE), LARGE_PRICE / 2.0, DELTA);
    }

    @Test
    void calculatePER_largePriceZeroDividend() {
        assertEquals(ZERO_FIXED_DIVIDEND_PREFERRED_STOCK.calculatePER(LARGE_PRICE), 0.0, DELTA);
    }

    @Test
    void calculatePER_largePriceLargeDividend() {
        assertEquals(LARGE_FIXED_DIVIDEND_PREFERRED_STOCK.calculatePER(LARGE_PRICE), LARGE_PRICE / 2500.0, DELTA);
    }

}
