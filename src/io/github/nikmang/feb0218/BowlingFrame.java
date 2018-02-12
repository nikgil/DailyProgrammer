package io.github.nikmang.feb0218;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: Garth5689
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7so37o/20180124_challenge_348_intermediate_bowling/
 * Difficulty: Intermediate
 * <p>
 * Test Cases [Answers]:
 * 6 4 5 3 10 10 8 1 8 0 10 6 3 7 3 5 3 [6/ 53 X  X  81 8- X  63 7/ 53]
 * 9  0  9  0  9  0  9  0  9  0  9  0  9  0  9  0  9  0  9  0
 * 10 10 10 10 10 10 10 10 10 10 10 10
 * 5  5  5  5  5  5  5  5  5  5  5  5  5  5  5  5  5  5  5  5  5
 * 10 3  7  6  1  10 10 10 2  8  9  0  7  3  10 10 10
 * 9  0  3  7  6  1  3  7  8  1  5  5  0  10 8  0  7  3  8  2  8
 */
public class BowlingFrame {
    private static String refactor(int[] hits) {
        StringBuilder builder = new StringBuilder();
        int turn = 1;
        int i = 0;

        while (turn < 10) {
            int throw1 = hits[i++];
            int throw2 = hits[i];

            builder.append(translateNum(throw1, throw2));

            if (throw1 != 10) {
                i++;
            }

            builder.append(" ");
            turn++;
        }

        if (hits[i] + hits[i + 1] >= 10) {
            if (hits[i] == 10) {
                builder.append("X");
                i++;

                //You can probably tell I got sick of trying to remain clean and consistent here
                if(hits[i] == 10) {
                    builder.append("X");

                    if(hits[i+1] == 10)
                        builder.append("X");
                    else
                        builder.append(hits[i+1]);
                } else {
                    builder.append(translateNum(hits[i++], hits[i]));
                }
            } else {
                builder.append(translateNum(hits[i++], hits[i++])).append(hits[i]);
            }
        } else {
            builder.append(translateNum(hits[i++], hits[i]));
        }

        return builder.toString().trim();
    }

    private static String translateNum(int num1, int num2) {
        String s1;
        String s2;

        if (num1 == 10) {
            return "X ";
        } else if (num1 == 0) {
            s1 = "-";
        } else {
            s1 = String.valueOf(num1);
        }

        if (num2 == 10) {
            s2 = "/";
        } else if (num2 == 0) {
            s2 = "-";
        } else if (num1 + num2 == 10) {
            s2 = "/";
        } else {
            s2 = String.valueOf(num2);
        }

        return s1 + s2;
    }

    private static int[] split(String s) {
        String[] split = s.split("\\s");
        int[] arr = new int[split.length];

        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.valueOf(split[i]);
        }

        return arr;
    }

    @Test
    public void testFrames() {
        Assert.assertEquals("6/ 53 X  X  81 8- X  63 7/ 53", refactor(split("6 4 5 3 10 10 8 1 8 0 10 6 3 7 3 5 3")));
        Assert.assertEquals("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", refactor(split("9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0 9 0")));
        Assert.assertEquals("X  X  X  X  X  X  X  X  X  XXX", refactor(split("10 10 10 10 10 10 10 10 10 10 10 10")));
        Assert.assertEquals("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5", refactor(split("5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5")));
        Assert.assertEquals("X  3/ 61 X  X  X  2/ 9- 7/ XXX", refactor(split("10 3 7 6 1 10 10 10 2 8 9 0 7 3 10 10 10")));
        Assert.assertEquals("9- 3/ 61 3/ 81 5/ -/ 8- 7/ 8/8", refactor(split("9 0 3 7 6 1 3 7 8 1 5 5 0 10 8 0 7 3 8 2 8")));
    }
}
