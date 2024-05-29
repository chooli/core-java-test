package core.java.algo.backtracking;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NumberCombinationTest extends CommonTest {

    @BeforeAll
    void setup () {
        //void
    }

    @Test
    void test () {
        List<List<Integer>> result = new ArrayList<>();
        int N = 4, K = 2, count = 0;
        List<Integer> subResult = Arrays.asList(new Integer[K]);
       //btLoop(K, count, List.of(1,2,3,4,5), subResult);
        backtrack(1, N, K, new ArrayList<>());

        //printList(subResult);
        assertTrue(true);
    }

    private void backtrack(int start, int N, int K, final List<Integer> subResult) {
        if (subResult.size() == K) {
            printList(subResult);
            Collections.singletonList(subResult);
            return;
        }

        for (int i=start; i<N+1; i++) {
            subResult.add(i);
            backtrack(i+1, N, K, subResult);
            subResult.removeLast();
        }
    }

    private void btLoop(int K, int count,  final List<Integer> list, final List<Integer> subResult) {
        if (count < K) {
            count++;
            for (final int num : list) {
                subResult.set(K-count, num);
                btLoop(K, count, list.stream().filter(val -> val!=num).toList(), subResult);
            }
        } else {
            printList(subResult);
        }
    }
}
