package core.java.algo.dp;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxSequenceTest {

    @Test
    void maxStairsTest() {
        int n = 50;
        long preStep1 = 2, preStep2 = 1, result = 0;
        for (int i=2; i<n; i++) {
            result = preStep1 + preStep2;
            preStep2 = preStep1;
            preStep1 = result;
        }

        assertEquals(20365011074L, result);
    }

    @Test
    void maxLongestSubsequenceTest() {
        int[] list = new int[]{4, 5, 6, 1, 2, 7, 8, 3, 4, 5, 6, 7, 8};




        assertTrue(true);
    }
}
