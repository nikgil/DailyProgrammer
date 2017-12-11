package io.github.nikmang.dec1117;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7j33iv/20171211_challenge_344_easy_baumsweet_sequence/
 * Difficulty: Easy
 *
 * Test Cases:
 * 20 [1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0]
 */
public class BaumSweet {

    public static String runThrough(int num) {
        StringBuilder sb = new StringBuilder("1, ");

        for(int i=1; i<=num; i++) {
            char[] binary = Integer.toBinaryString(i).toCharArray();
            int len = 0;

            for(int j=0; j<binary.length; j++) {
                if(binary[j]=='1') {
                    if(len%2==0) {
                        len = 0;
                    } else {
                        break;
                    }
                } else {
                    len++;
                }
            }

            if(len%2==0) {
                sb.append("1, ");
            } else {
                sb.append("0, ");
            }
        }

        return sb.delete(sb.length()-2, sb.length()).toString();
    }

    @Test
    public void testRun() {
        Assert.assertEquals("1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0", runThrough(20));
    }

}
