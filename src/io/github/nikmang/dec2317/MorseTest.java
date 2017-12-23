package io.github.nikmang.dec2317;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Nik on 12/23/2017.
 */
public class MorseTest {

    @Test
    public void testToMorse() {
        String morse = MorseTree.MORSE_TREE.getMorse("SOS");

        Assert.assertEquals("...---...", morse);
    }

    @Test
    public void testToString() {
        String normal = MorseTree.MORSE_TREE.getWord("... --- ...");

        Assert.assertEquals("SOS", normal);

        String code2 = "- .... .. ... / .. ... / .- / ... .. -- .--. .-.. . --..-- / -.-- . - / -.-. .... .- .-.. .-.. . -. --. .. -. --. / -.-. .- -.-. .... . .-.-.-";
        String[] split = code2.split("/");
        StringBuilder sb = new StringBuilder();

        for(String s : split) {
            if(s.startsWith(" "))
                s = s.substring(1);
            sb.append(MorseTree.MORSE_TREE.getWord(s)).append(" ");
        }

        Assert.assertEquals("This is a simple, yet challenging cache.".toUpperCase(), sb.toString().trim());
    }
}
