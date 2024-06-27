package core.java.algo.dp;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DivideAndConquerTest extends CommonTest {

    int[] sorted, unsorted = new int[]{-23, 1, 3, -2, -8, 0, 9, 4, -7};

    @BeforeAll
    public void setUp() {
        sorted = new int[]{1, 3, 9, 11, 12};

        assertTrue(true);
    }

}
