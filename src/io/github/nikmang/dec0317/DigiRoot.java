package io.github.nikmang.dec0317;


import org.junit.Assert;
import org.junit.Test;

/**
 * Author: nint22
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/1berjh/040113_challenge_122_easy_sum_them_digits/?st=jarb7uvm&sh=de0ad2df
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * 31337 [8]
 */
public class DigiRoot {

    public int getRoot(int i) {
        while(i > 9) {
            i = i%10 + getRoot(i/10);
        }

        return i;
    }

    @Test
    public void testRoot() {
        Assert.assertEquals(8, getRoot(31337));
    }
}