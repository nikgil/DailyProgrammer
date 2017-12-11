package io.github.nikmang.nov2817;


import org.junit.Assert;
import org.junit.Test;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7cnqtw/20171113_challenge_340_easy_first_recurring/?st=jandfpw0&sh=f90df698
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * (No output provided)
 * IKEUNFUVFV
 * PXLJOUDJVZGQHLBHGXIW
 * *l1J?)yn%R[}9~1"=k7]9;0[$
 */
public class RepeatingChars {

    private static int repeatNum(String str) {

        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);

            if(str.indexOf(c) != str.lastIndexOf(c)) {
                return i;
            }
        }
        return -1;
    }

    @Test
    void testRepeatNum() {
        Assert.assertEquals(3, repeatNum("IKEUNFUVFV"));
        Assert.assertEquals(1, repeatNum("PXLJOUDJVZGQHLBHGXIW"));
        Assert.assertEquals(2, repeatNum("*l1J?)yn%R[}9~1\"=k7]9;0[$"));
    }
}
