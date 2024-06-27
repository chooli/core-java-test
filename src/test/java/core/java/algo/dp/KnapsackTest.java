package core.java.algo.dp;

import core.java.CommonTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        printf("minimum cost to the top is %s", result);
        assertTrue(true);
    }

    int min = Integer.MAX_VALUE;
    int NOT_FOUND = 9999;
    int numOfIteration = 0;

    @Test
    void test() {
        int target = 10;
        int[] coins = {2, 3, 5};
        //bruteforce(10, coins, 0);
        //min = dp(target, coins);

        //dfsBFNoReuse(target, IntStream.of(coins).boxed().toList(), 1);
        min = dfsDPNoReuse(target, IntStream.of(coins).boxed().toList(), 0, new TreeSet<>(), new HashMap<>());
        printf("min: %s, iteration: %s", min, numOfIteration);
        //min.set(Integer.MAX_VALUE);
        //minCoinsCount2(target, coins, count, min);
        assertTrue(true);
    }

    private void dfsBF(int target, int[] coins, int count) {
        for (int coin : coins) {
            if (target == coin) {
                min = Math.min(min, count+1);
                return;
            } else if (target > coin) {
                dfsBF(target - coin, coins, count+1);
            } else {
                return;
            }
        }
    }

    private int dp(int target, int[] coins) {
        int[] minMap = new int[target+1];
        Arrays.fill(minMap, 9999);
        minMap[0] = 0;
        for (int i=1; i<=target; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    int previous = minMap[i - coin];
                    if (previous != target+1 && previous != -1) {
                        minMap[i] = Math.min(minMap[i], previous + 1);
                    }
                    if (minMap[i] == 1){
                        break;
                    }
                }
            }
            if (minMap[i] == target+1) minMap[i] = -1;
        }
        return minMap[target];
    }

    private void dfsBFNoReuse(int target, List<Integer> coins, int count) {
        if (coins.size() == 0) {
            return;
        }
        for (int coin : coins) {
            if (target == coin) {
                min = Math.min(min, count);
            } else {
                dfsBFNoReuse(target - coin, coins.stream().filter(c -> !c.equals(coin)).toList(), count + 1);
            }
        }
    }

    private int dfsDPNoReuse(int target, List<Integer> coins, int count, Set<Integer> visited, Map<String, Integer> minMap) {
        numOfIteration++;
        if (target == 0) {
            return count;
        } else if (target < 0 || coins.size() == 0) {
            return NOT_FOUND;
        }
        //memorization
        String key = visited.toString();
        if (minMap.containsKey(key)) {
            return minMap.get(key);
        }

        int min = NOT_FOUND;
        for (int coin : coins) {
            visited.add(coin);
            int result = dfsDPNoReuse(target - coin,
                    coins.stream().filter(c -> !c.equals(coin)).toList(), count + 1, visited, minMap);
            min = Math.min(min, result);
            minMap.put(visited.toString(), min);
            visited.remove(coin);
        }
        return min;
    }

    private char coinToChar(int coin) {
        return (char)(coin+'0');
    }

    private int dpNoReuse(int target, int[] coins) {
        int[] counts = new int[target + 1];
        Map<Integer, List<List<Integer>>> coinsMap = new HashMap<>();
        coinsMap.put(0, List.of(List.of()));
        Arrays.fill(counts, NOT_FOUND);
        counts[0] = 0;
        for (int coin : coins) {
            //take
            for (int i=coin; i<=target; i++) {
                int prevIdx = i-coin;
                if (isCoinUsed(coin, prevIdx, coinsMap)) {
                    continue;
                }
                counts[i] = Math.min(counts[i], 1 + counts[prevIdx]);
                //add to used map
                if (counts[i] < NOT_FOUND) {
                    addCoinUsed(coin, i, prevIdx, coinsMap);
                }
            }
        }

        return counts[target] == NOT_FOUND ? -1 : counts[target];
    }

    private boolean isCoinUsed(int coin, int preIdx, Map<Integer, List<List<Integer>>> coinsMap) {
        if (coinsMap.containsKey(preIdx)) {
            for (List<Integer> list : coinsMap.get(preIdx)) {
                if(!list.contains(coin)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void addCoinUsed(int coin, int idx, int preIdx, Map<Integer, List<List<Integer>>> coinsMap) {
        if (coinsMap.containsKey(idx)) {
            List<List<Integer>> list = coinsMap.get(idx);
            List<Integer> newList = Collections.singletonList(coin);
            list.add(newList);
        } else {
            List<Integer> coins;
            List<List<Integer>> list = new ArrayList<>();
            for (List<Integer> preList : coinsMap.get(preIdx)) {
                coins = new ArrayList<>(List.of(coin));
                if (!preList.contains(coin)) {
                    coins.addAll(preList);
                    list.add(coins);
                }
            }
            coinsMap.put(idx, list);
        }
    }
}
