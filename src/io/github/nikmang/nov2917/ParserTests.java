package io.github.nikmang.nov2917;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Nik on 11/30/2017.
 */
public class ParserTests {

    @Test
    void testConstructor() {
        boolean isValid = true;

        try {
            List<String> lines = new PacketParser().lines;

            Assert.assertEquals(64, lines.size());
        } catch (IOException | URISyntaxException e) {
            isValid = false;
            e.printStackTrace();
        }

        Assert.assertTrue(isValid);
    }

    @Test
    void testParse() {
        try {
            PacketParser p = new PacketParser();
            p.parse();

            Assert.assertEquals(5, p.packetData.size());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPrint() {
        try {
            PacketParser p = new PacketParser();
            p.parse();

            p.print();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
