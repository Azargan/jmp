package com.epam.mentoring.task3;

import com.google.common.base.Strings;
import java.math.BigInteger;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public class Utils {

    public static String concatenateWords(final String first, final String second) {

        if (first == null && second == null) {
            return null;
        }
        
        String a = Strings.nullToEmpty(first);
        String b = Strings.nullToEmpty(second);

        return a + b;
    }

    public static BigInteger computeFactorial(Integer number) throws IllegalArgumentException {
        
        if (number == null || number < 0) {
            throw new IllegalArgumentException("Argument can't be null or less than zero");
        }
        
        BigInteger factorial = BigInteger.ONE;
        while (number > 0) {            
            factorial = factorial.multiply(BigInteger.valueOf(number--));
        }
        
        return factorial;
    }

    public static String normalizeWord(final String word) {
        return null;
    }

}
