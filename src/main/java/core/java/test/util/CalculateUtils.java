package core.java.test.util;

import java.math.BigInteger;

public class CalculateUtils {

    private CalculateUtils(){
        //void
    }

    public static BigInteger factorial(BigInteger number) {

        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number.longValue(); factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }
}
