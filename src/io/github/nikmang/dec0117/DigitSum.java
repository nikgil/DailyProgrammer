package io.github.nikmang.dec0117;


import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Author: MasterAgent47
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/6zvjre/20170913_challenge_331_intermediate_sum_of_digits/?st=jar5gisi&sh=385f1995
 * Difficulty: Medium
 *
 * Test cases [Output]:
 * 2 1234 [1636]
 * 11 4000 [18313]
 * 50 3000 [9208]
 */
public class DigitSum {

    static long getSum(int base, int power) {
        BigInteger bd = new BigInteger(String.valueOf(base));
        bd = bd.pow(power);

        long sum = 0;

        String s = bd.toString();
        for(int i=0; i<s.length(); i++) {
            String c = s.substring(i,i+1);

            sum += Integer.valueOf(c);
        }

        return sum;
    }

    @Test
    public void testDigitSum() {
        Assert.assertEquals(1636, getSum(2,1234));
        Assert.assertEquals(18313, getSum(11,4000));
        Assert.assertEquals(9208, getSum(50,3000));
    }
}
