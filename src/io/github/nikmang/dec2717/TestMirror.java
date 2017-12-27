package io.github.nikmang.dec2717;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Created by Nik on 12/27/2017.
 */
public class TestMirror {

    @Test
    public void testSimpleMirror() {
        //Avoid boilerplate code
        String testString = "TpnQSjdmZdpoohd";
        String key =    "   \\\\  /\\    \n" +
                        "            \\\n" +
                        "   /         \n" +
                        "      \\     \\\n" +
                        "    \\        \n" +
                        "  /      /   \n" +
                        "\\  /      \\  \n" +
                        "     \\       \n" +
                        "\\/           \n" +
                        "/            \n" +
                        "          \\  \n" +
                        "    \\/       \n" +
                        "   /       / ";

        char[][] keyArray = new char[13][13];
        String[] rows = key.split("\n");

        for(int i=0; i<rows.length; i++) {
            keyArray[i] = rows[i].toCharArray();
        }

        MirrorEncrypt me = new MirrorEncrypt(keyArray);

        Assert.assertEquals("DailyProgrammer", me.translateWord(testString));
    }
}
