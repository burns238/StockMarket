package com.burnsm.stockmarket;

import java.util.List;
import static java.util.Arrays.asList;

public final class SampleStocks {

	public static final CommonStock TEA_COMMON_STOCK = new CommonStock("TEA", 0.0, 100.0);
	public static final CommonStock POP_COMMON_STOCK = new CommonStock("POP", 8.0, 100.0);
	public static final CommonStock ALE_COMMON_STOCK = new CommonStock("ALE", 23.0, 60.0);
	public static final PreferredStock GIN_PREFERRED_STOCK = new PreferredStock("GIN", 8.0, 100.0, 2.0);
	public static final CommonStock JOE_COMMON_STOCK = new CommonStock("JOE", 13.0, 250.0);
	
	public static final List<Stock> ALL_STOCKS = asList(TEA_COMMON_STOCK,
														POP_COMMON_STOCK,
														ALE_COMMON_STOCK,
														GIN_PREFERRED_STOCK,
														JOE_COMMON_STOCK);
	
}
