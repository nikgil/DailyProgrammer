package io.github.nikmang.dec1417;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Nik on 12/16/2017.
 */
public class TestPokeRequest {

    @Test
    public void testRequests() {
        try {
            Assert.assertEquals("2.0x", PokeRequest.getDamageType("fire", "grass"));
            Assert.assertEquals("4.0x", PokeRequest.getDamageType("fighting", "ice", "rock"));
            Assert.assertEquals("0.0x", PokeRequest.getDamageType("psychic", "poison", "dark"));
            Assert.assertEquals("1.0x", PokeRequest.getDamageType("water", "normal"));
            Assert.assertEquals("0.5x", PokeRequest.getDamageType("fire", "rock"));

        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("IOException happened");
        }
    }
}
