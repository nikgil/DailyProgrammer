package io.github.nikmang.dec2117;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: nint22
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/1qwkdz/111113_challenge_141_easy_checksums/
 * Difficulty: Easy
 * Input [Output]:
 * Fletcher [D330]
 * Sally sells seashells by the seashore. [D23E]
 * Les chaussettes de l'archi-duchesse, sont-elles seches ou archi-seches ? [404D]
 */
public class Checksum {

    private static int fletcher(String input) {
        int sum1 = 0;
        int sum2 = 0;

        for(char c : input.toCharArray()) {
            sum1 = (sum1 + c) % 255;
            sum2 = (sum2 + sum1) % 255;
        }

        return (sum2 << 8) | sum1;
    }

    @Test
    public void testStrings() {
        String[] in = {"Fletcher", "Sally sells seashells by the seashore.", "Les chaussettes de l'archi-duchesse, sont-elles seches ou archi-seches ?"};
        String[] out = {"d330", "d23e", "404d"};

        for(int i=0; i<in.length; i++) {
            String checksum = String.format("%x", fletcher(in[i]));

            Assert.assertEquals(out[i], checksum);
        }
    }
}
