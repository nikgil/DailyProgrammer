package io.github.nikmang.dec2217;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author: oskar_s
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/t33vi/522012_challenge_47_easy/
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * Hello 3 [Khoor]
 * Daily programmer 6 [Jgore vxumxgsskx]
 * Jgore vxumxgsskx 20 [Daily programmer]
 */
public class Caesar {

    public static void main(String[] args) {
        String s = "Spzalu - zayhunl dvtlu sfpun pu wvukz kpzaypibapun zdvykz pz uv ihzpz mvy h \n" +
                "zfzalt vm nvclyutlua.  Zbwyltl leljbapcl wvdly klypclz myvt h thukhal myvt aol \n" +
                "thzzlz, uva myvt zvtl mhyjpjhs hxbhapj jlyltvuf. Fvb jhu'a lewlja av dplsk \n" +
                "zbwyltl leljbapcl wvdly qbza 'jhbzl zvtl dhalyf ahya aoyld h zdvyk ha fvb! P \n" +
                "tlhu, pm P dlua hyvbuk zhfpu' P dhz hu ltwlylyvy qbza iljhbzl zvtl tvpzalulk \n" +
                "ipua ohk sviilk h zjptpahy ha tl aolf'k wba tl hdhf!... Ho, huk uvd dl zll aol \n" +
                "cpvslujl puolylua pu aol zfzalt! Jvtl zll aol cpvslujl puolylua pu aol zfzalt! \n" +
                "Olsw! Olsw! P't ilpun ylwylzzlk!";

        System.out.println(cipher(s, 97));
    }
    public static String cipher(String input, int shift) {
        shift = shift%26; //In case this goes over 26
        StringBuilder sb = new StringBuilder();

        for(char c : input.toCharArray()) {
            if(Character.isLetter(c)) {
                //int toShift = c+shift;
                int shifted = c + shift;

                if(Character.isUpperCase(c)) {
                    if(shifted > 90) {
                        shifted = 64 + (shifted%90);
                    }
                } else {
                    if(shifted > 122) {
                        shifted = 96 + (shifted%122);
                    }
                }

                sb.append((char)shifted);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @Test
    public void testStrings() {
        Assert.assertEquals("Khoor", cipher("Hello", 3));
        Assert.assertEquals("Jgore vxumxgsskx", cipher("Daily programmer", 6));
        Assert.assertEquals("Daily programmer", cipher("Jgore vxumxgsskx", 20));

    }
}
