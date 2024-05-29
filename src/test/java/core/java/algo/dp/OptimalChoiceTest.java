package core.java.algo.dp;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OptimalChoiceTest extends CommonTest {

    String[] options = {"water", "book", "food", "jacket", "camera"};
    double[] optionValues = {3, 1, 2, 2, 1};
    int[] weightings = {10, 3, 9, 5, 6};
    int MAX_VALUE = 6;
    double INTERVAL = 1;

    @BeforeAll
    void setUp() {
        //void
    }

    @Test
    void test() {

        int l = options.length, lx = (int)(MAX_VALUE/INTERVAL);
        int[][] maxWeightings = new int[l][lx];

        for (int i=0; i<l; i++) {
            int currentWeight = weightings[i];
            for (int j=0; j<lx; j++) {
                double daySpend = idxToOptionValue(j);
                if ( optionValues[i] == daySpend ) {
                    maxWeightings[i][j] = i > 0 ? Math.max(currentWeight, maxWeightings[i-1][j]) : currentWeight;
                } else if (optionValues[i] < daySpend) {
                    int idx = optionValueToIdx(daySpend - optionValues[i]);
                    maxWeightings[i][j] = i > 0 ? Math.max(maxWeightings[i-1][idx] + currentWeight, maxWeightings[i-1][j]) : currentWeight;
                } else {
                    maxWeightings[i][j] = i > 0 ? maxWeightings[i-1][j] : 0;
                }
            }
        }

        int optimalMaxWeight = maxWeightings[l-1][lx-1];
        println("max weight = " + optimalMaxWeight);
        assertTrue(true);
    }

    double idxToOptionValue (int idx) {
        return (idx + 1) * INTERVAL;
    }

    int optionValueToIdx (double optionVal) {
        return (int)(optionVal / INTERVAL - 1);
    }
}
