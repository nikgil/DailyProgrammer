package io.github.nikmang.sep2318;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Nik Gil
 * Date: 23/9/2018
 */
public class CommissionTests {

    @Test
    public void testCommission() {
        String[] names = {"Frank", "Jane"};
        int[][] revenue = {{120, 145}, {243, 265}};
        int[][] expenses = {{130, 59}, {143, 198}};

        Assert.assertArrayEquals(new long[]{6, 9}, Commission.getCommission(0.062, names, revenue, expenses));

        names = new String[]{"Johnver", "Vanston", "Danbree", "Vansey", "Mundyke"};
        revenue = new int[][]{
                {190, 140, 1926, 14, 143},
                {325, 19, 293, 1491, 162},
                {682, 14, 852, 56, 659},
                {829, 140, 609, 120, 87}
        };

        expenses = new int[][]{
                {120, 65, 890, 54, 430},
                {300, 10, 23, 802, 235},
                {50, 299, 1290, 12, 145},
                {67, 254, 89, 129, 76}
        };

        Assert.assertArrayEquals(new long[]{92, 5, 113, 45, 32}, Commission.getCommission(0.062, names, revenue, expenses));
    }
}
