package io.github.nikmang.dec0517;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SubArrayTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3}, 1, 0}, {new int[]{7,3,2,5,6}, 11, 3}
        });
    }

    private int[] nums;
    private int max;
    private int expected;

    public SubArrayTest(int[] nums, int max, int expected) {
        this.nums = nums;
        this.max = max;
        this.expected = expected;
    }

    @Test
    public void testArray() {
        MinSubArray arr = new MinSubArray(nums, max);

        assertEquals(expected, arr.getLength());
    }
}
