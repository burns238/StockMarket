package com.burnsm.stockmarket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.burnsm.stockmarket.stock.AllShareIndex;
import com.burnsm.stockmarket.stock.SampleStocks;
import com.burnsm.stockmarket.stock.Stock;
import com.burnsm.stockmarket.stock.Trade;

@RestController
public class StockMarketRestController {

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    @GetMapping(path = "/stock")
    public List<Stock> retrieveAllStocks() {
        return new ArrayList<>(SampleStocks.getAllStocks().values());
    }

    @GetMapping(path = "/stock/{id}/")
    public Stock retrieveStock(@PathVariable String id) {
        Map<String, Stock> stockMap = SampleStocks.getAllStocks();
        if (stockMap.containsKey(id)) {
            return stockMap.get(id);
        } else {
            throw new IllegalArgumentException("Stock not found");
        }
    }

    @GetMapping(path = "/stock/{id}/dividendyield/{price}/")
    public double getDividendYield(@PathVariable String id, @PathVariable double price) {
        Map<String, Stock> stockMap = SampleStocks.getAllStocks();
        if (stockMap.containsKey(id)) {
            Stock stock = stockMap.get(id);
            return stock.calculateDividendYield(price);
        } else {
            throw new IllegalArgumentException("Stock not found");
        }
    }

    @GetMapping(path = "/stock/{id}/peratio/{price}/")
    public double getPERatio(@PathVariable String id, @PathVariable double price) {
        Map<String, Stock> stockMap = SampleStocks.getAllStocks();
        if (stockMap.containsKey(id)) {
            Stock stock = stockMap.get(id);
            return stock.calculatePER(price);
        } else {
            throw new IllegalArgumentException("Stock not found");
        }
    }

    @PostMapping(path = "/stock/{id}/trade/")
    @ResponseStatus(HttpStatus.CREATED)
    public void recordTrade(@PathVariable String id, @RequestBody Trade trade) {
        if (trade.getBuyOrSell() == null) {
            throw new IllegalArgumentException("buyOrSell must be either BUY or SELL");
        }

        Map<String, Stock> stockMap = SampleStocks.getAllStocks();
        if (stockMap.containsKey(id)) {
            Stock stock = stockMap.get(id);
            stock.recordTrade(trade);
        } else {
            throw new IllegalArgumentException("Stock not found");
        }
    }

    @GetMapping(path = "/stock/{id}/volumeweightedstockprice/")
    public double getVolumeWeightedStockPrice(@PathVariable String id) {
        Map<String, Stock> stockMap = SampleStocks.getAllStocks();
        if (stockMap.containsKey(id)) {
            Stock stock = stockMap.get(id);
            return stock.calculateVolumeWeightedStockPrice();
        } else {
            throw new IllegalArgumentException("Stock not found");
        }
    }

    @GetMapping(path = "/stock/allshareindex/")
    public double getAllShareIndex() {
        List<Stock> stocks = new ArrayList<>(SampleStocks.getAllStocks().values());
        return AllShareIndex.calculateAllShareIndex(stocks);
    }

}
