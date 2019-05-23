package com.burnsm.stockmarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommonStockTests {
	
	private double LARGE_PRICE = 12345;
	private double DELTA = 0.0000001;

    @Test
    void calculateDividendYield_zeroPriceNonZeroDividend() {
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculateDividendYield(0), 0.0, DELTA);
    }
    
    @Test
    void calculateDividendYield_zeroPriceZeroDividend() {
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculateDividendYield(0), 0.0, DELTA);
    }
    
    @Test
    void calculateDividendYield_OnePriceNonZeroDividend() {
    	double joeDiv = SampleStocks.JOE_COMMON_STOCK.getLastDividend();
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculateDividendYield(1), joeDiv, DELTA);
    }
    
    @Test
    void calculateDividendYield_OnePriceZeroDividend() {
    	double teaDiv = SampleStocks.TEA_COMMON_STOCK.getLastDividend();
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculateDividendYield(1), teaDiv, DELTA);
    }
    
    @Test
    void calculateDividendYield_TwoPriceNonZeroDividend() {
    	double joeDiv = SampleStocks.JOE_COMMON_STOCK.getLastDividend();
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculateDividendYield(2), joeDiv/2, DELTA);
    }
    
    @Test
    void calculateDividendYield_TwoPriceZeroDividend() {
    	double teaDiv = SampleStocks.TEA_COMMON_STOCK.getLastDividend();
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculateDividendYield(2), teaDiv/2, DELTA);
    }    
    
    @Test
    void calculateDividendYield_LargePriceNonZeroDividend() {
    	double joeDiv = SampleStocks.JOE_COMMON_STOCK.getLastDividend();
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculateDividendYield(LARGE_PRICE), joeDiv/LARGE_PRICE, DELTA);
    }
    
    @Test
    void calculateDividendYield_LargePriceZeroDividend() {
    	double teaDiv = SampleStocks.TEA_COMMON_STOCK.getLastDividend();
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculateDividendYield(LARGE_PRICE), teaDiv/LARGE_PRICE, DELTA);
    }   
    
    @Test
    void calculatePER_zeroPriceNonZeroDividend() {
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculatePER(0), 0.0, DELTA);
    }
    
    @Test
    void calculatePER_zeroPriceZeroDividend() {
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculatePER(0), 0.0, DELTA);
    }
    
    @Test
    void calculatePER_onePriceNonZeroDividend() {
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculatePER(1), 1.0/13.0, DELTA);
    }
    
    @Test
    void calculatePER_onePriceZeroDividend() {
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculatePER(1), 0.0, DELTA);
    }
    
    @Test
    void calculatePER_largePriceNonZeroDividend() {
    	assertEquals(SampleStocks.JOE_COMMON_STOCK.calculatePER(LARGE_PRICE), LARGE_PRICE/13.0, DELTA);
    }
    
    @Test
    void calculatePER_largePriceZeroDividend() {
    	assertEquals(SampleStocks.TEA_COMMON_STOCK.calculatePER(LARGE_PRICE), 0.0, DELTA);
    } 
    
}
