package com.burnsm.stockmarket.stock;

import java.util.HashMap;
import java.util.Map;

public final class SampleStocks {

    public static final String TEA = "TEA";
    public static CommonStock teaCommonStock = new CommonStock(TEA, 0.0, 100.0);
    public static final String POP = "POP";
    public static CommonStock popCommonStock = new CommonStock(POP, 8.0, 100.0);
    public static final String ALE = "ALE";
    public static CommonStock aleCommonStock = new CommonStock(ALE, 23.0, 60.0);
    public static final String GIN = "GIN";
    public static PreferredStock ginPreferredStock = new PreferredStock(GIN, 8.0, 100.0, 2.0);
    public static final String JOE = "JOE";
    public static CommonStock joeCommonStock = new CommonStock(JOE, 13.0, 250.0);

    public static Map<String, Stock> getAllStocks() {

        Map<String, Stock> allStocks = new HashMap<String, Stock>();
        allStocks.put(TEA, teaCommonStock);
        allStocks.put(POP, popCommonStock);
        allStocks.put(ALE, aleCommonStock);
        allStocks.put(GIN, ginPreferredStock);
        allStocks.put(JOE, joeCommonStock);

        return allStocks;
    }

}
