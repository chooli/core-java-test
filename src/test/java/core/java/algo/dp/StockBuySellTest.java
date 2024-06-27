package core.java.algo.dp;

import core.java.CommonTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StockBuySellTest extends CommonTest {

    int max = 0;

    @Test
    void test() {
        int[] prices = {1, 3, 1, 2, 4, 6, 34, 24,56,78,90,12,45,75,32};
        Map<String, Integer> profits = new HashMap<>();

        // brute force
//        dfsBF(0, prices, true, 0);
//        printf("max profit: %s", max);
//
//        max = iterateWithoutCoolDown(prices);
//        printf("max profit: %s", max);

        max = iterateWithCoolDown(prices);
        printf("max with cool down: %s", max);

        int maxProfit = dfsWithDP(0, prices, true, profits);
        printf("max with cool down: %s", maxProfit);
    }

    private void dfsBF(int idx, int[] prices, boolean buy, int profit) {
        if (idx >= prices.length) {
            return;
        }

        dfsBF(idx+1, prices, buy, profit);
        if (buy) {
            int buyProfit = profit - prices[idx];
            dfsBF(idx+1, prices, !buy, buyProfit);

        } else {
            int sellProfit = profit + prices[idx];
            max = Math.max(max, sellProfit);
            dfsBF(idx+1, prices, !buy, sellProfit);
        }
    }

    private int iterateWithoutCoolDown(int[] prices) {
        int maxBuy = -prices[0];
        int maxSell = 0;
        for (int i=1; i<prices.length; i++) {
            //when buy
            int buyProfit = maxSell - prices[i];
            //when sell
            int sellProfit = maxBuy + prices[i]; // not able to sell at first
            maxBuy = Math.max(maxBuy, buyProfit);
            maxSell = Math.max(maxSell, sellProfit);
        }
        return Math.max(maxBuy, maxSell);
    }

    private int iterateWithCoolDown(int[] prices) {
        int maxBuy = -prices[0];
        int maxSell = 0, prevSell = 0;
        for (int i=1; i<prices.length; i++) {
            //when buy
            int buyProfit = maxSell - prices[i];
            //when sell
            int sellProfit = maxBuy + prices[i]; // not able to sell at first
            maxBuy = Math.max(maxBuy, buyProfit);
            maxSell = Math.max(maxSell, prevSell);
            prevSell = sellProfit;
        }
        return Math.max(prevSell, Math.max(maxBuy, maxSell));
    }

    private int dfsWithDP(int idx, int[] prices, boolean buy, Map<String, Integer> profits) {
        if (idx >= prices.length) {
            return 0;
        }
        String key = idx + "-" + buy;
        if (profits.containsKey(key)) {
            return profits.get(key);
        }

        int profit1;
        int profit2 = dfsWithDP(idx+1, prices, buy, profits);  // cool down one day
        if (buy) {
            profit1 = dfsWithDP(idx + 1, prices, !buy, profits) - prices[idx];
            profits.put(key, Math.max(profit1, profit2));
        } else {
            profit1 = dfsWithDP(idx + 2, prices, !buy, profits) + prices[idx];
            profits.put(key, Math.max(profit1, profit2));
        }

        return profits.get(key);
    }
}
