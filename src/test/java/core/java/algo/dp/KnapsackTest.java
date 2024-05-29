package core.java.algo.dp;

import core.java.CommonTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KnapsackTest extends CommonTest {

    @Test
    void minCostToTop() {
        int[] stairCosts = {1,100,1,1,1,100,1,1,23,1};
        int l = stairCosts.length,  preCost1 = 0, preCost2 = 0;
        for (int i=0; i<l; i++) {
            int current = stairCosts[i] + Math.min(preCost1, preCost2);
            preCost2 = preCost1;
            preCost1 = current;
        }
        int result = Math.min(preCost1, preCost2);
        System.out.println("minimum cost to the top is " + result);
        assertTrue(true);
    }

    int min = Integer.MAX_VALUE;

    @Test
    void minimumCoinsToTarget() {
//        int target = 12;
      int[] coins = {1, 2, 3, 7, 9, 11};
//        int count = 0;
//        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
//        minCoinsCount11(target, coins, count, min);
//        int result12 = minCoinsCount12(target, coins);
        //assertEquals(4, result12);
        calculateCoins(10, coins, 0);
        println(min);
        //min.set(Integer.MAX_VALUE);
        //minCoinsCount2(target, coins, count, min);
        //assertEquals(2, min.get());
    }

    private void calculateCoins(int num, int[] coins, int count) {
        for (int coin : coins) {
            if (num == coin) {
                min = Math.min(min, count+1);
                return;
            } else if (num > coin) {
                calculateCoins(num - coin, coins, count+1);
            } else {
                return;
            }
        }
    }

    /**
     * Bounded Knapsack
     *
     * @param target
     * @param coins
     * @param count
     * @param min
     */
    private static void minCoinsCount11(int target, int[] coins, int count, AtomicInteger min) {
        for (int coin : coins) {
            if (target == coin || target == 1) {
                min.set(Math.min(min.get(), count+1));
                return;
            } else if (target > coin) {
                int[] newCoins = Arrays.stream(coins).filter(c -> c != coin).toArray();
                minCoinsCount11(target - coin, newCoins, count+1, min);
            }
        }
    }

    private int minCoinsCount12(int target, int[] coins) {

        Map<Integer, List<Set<Integer>>> possibleMap = new HashMap<>();
        List<Set<Integer>> list = possibleMap.putIfAbsent(1, Arrays.asList(new HashSet<>()));
        list.add(null);


        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        for (int i=1; i<=target; i++) {
            min.set(Integer.MAX_VALUE);
            List<Set<Integer>> currentList = currentList = new ArrayList<>();
            for (int coin : coins) {
                int prevTarget = i - coin;

                if (i == coin) {
                    currentList.add(Set.of(coin));
                    min.set(1);
                } else if (prevTarget > 0 && possibleMap.containsKey(prevTarget)) {
                    List<Set<Integer>> preList = coinToList(coin, prevTarget, possibleMap.get(prevTarget), min);
                    currentList.addAll(preList);
                }
            }
            possibleMap.put(i, currentList);
        }
        return min.get() == Integer.MAX_VALUE ? 0 : min.get();
    }

    private List<Set<Integer>> coinToList(int coin, int preTarget, List<Set<Integer>> prevList, AtomicInteger min) {
        List<Set<Integer>> newList = new ArrayList<>();
        for (Set<Integer> set : prevList) {
            if (!set.contains(coin)) {
                Set<Integer> newSet = new HashSet<>();
                newSet.add(coin);
                newSet.addAll(set);
                newList.add(newSet);
                min.set(Math.min(min.get(), newSet.size()));
            }
        }
        return newList;
    }

    /**
     * Unbounded Knapsack
     *
     * @param target
     * @param coins
     * @param count
     * @param min
     */
    private void minCoinsCount2(int target, int[] coins, int count, AtomicInteger min) {
        for (int coin : coins) {
            if (target == coin) {
                min.set(Math.min(min.get(), count+1));
                return;
            } else if (target > coin) {
                minCoinsCount2(target - coin, coins, count+1, min);
            } else if (target < 1) {
                return;
            }
        }
    }
}
