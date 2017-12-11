package io.github.nikmang.dec0617;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Nik on 12/6/2017.
 */
public class CarRentTest {

    @Test
    public void testRent() {
        CarRent rental = new CarRent(new int[]{ 1, 10,  5, 12, 13, 40, 30, 22,  70, 19},
                                     new int[]{23, 12, 10, 29, 25, 66, 35, 33, 100, 65});

        Stack<Tuple> tuples = rental.getMaxReturn();

        Assert.assertEquals(5, tuples.size());
    }
}
