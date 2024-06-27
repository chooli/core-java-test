package core.java.algo.dp;

import core.java.CommonTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EditDistanceTest extends CommonTest {

    Map<String, Integer> memo = new HashMap<>();
    String word = "dinitrophenylhydrazine", target = "acetylphenylhydrazine";

    @Test
    void test() {
        //int min = dfs(word, target);
        int[][] countMap = new int[word.length()][target.length()];
        for (int[] row : countMap) {
            Arrays.fill(row, -1);
        }
        int min = dfsTwoPointers(word, 0, target, 0, countMap);
        printf("min = %s", min);

        assertEquals(6, min);
    }

    private int dfs(String word, String target) {
        if (word.equals(target)) {
            return 0;
        }

        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        int i = 0;
        while(i < word.length() && i < target.length() && word.charAt(i) == target.charAt(i)) {
            i++;
        }

        int delete = Integer.MAX_VALUE, insert = delete, replace = delete;
        //insert
        if (i < target.length()) {
            String change = word.substring(0, i) + target.charAt(i) + word.substring(i);
            insert = dfs(change, target);
        }
        //delete
        if (i < word.length()) {
            String change = word.substring(0, i) + word.substring(i + 1);
            delete = dfs(change, target);
        }

        //replace
        if (i < word.length() && i < target.length()) {
            String change = word.substring(0, i) + target.charAt(i) + word.substring(i + 1);
            replace = dfs(change, target);
        }
        int count = 1 + Math.min(delete, Math.min(insert, replace));
        //System.out.println("word:"+word+" min:"+min);
        memo.put(word, count);

        return count;
    }

    private int dfsTwoPointers(String word, int i, String target, int j, int[][] countMap) {
        if (i >= word.length()) {
            return target.length() - j;
        }
        if (j >= target.length()) {
            return word.length() - i;
        }

        if (countMap[i][j] != -1) {
            return countMap[i][j];
        }
        //unchange
        if (word.charAt(i) == target.charAt(j)) {
            return dfsTwoPointers(word, i + 1, target, j + 1, countMap);
        }
        //insert
        int insert = dfsTwoPointers(word, i, target, j + 1, countMap);
        //delete
        int delete = dfsTwoPointers(word, i + 1, target, j, countMap);
        //replace
        int replace = dfsTwoPointers(word, i + 1, target, j + 1, countMap);

        int min = 1 + Math.min(insert, Math.min(delete, replace));
        countMap[i][j] = min;

        return min;
    }
}
