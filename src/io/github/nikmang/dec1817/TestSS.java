package io.github.nikmang.dec1817;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nik on 12/19/2017.
 */
public class TestSS {

    Map<String, String> pairs;
    SecretSanta ss;

    @Before
    public void getPairs() {
        try {
            ss = new SecretSanta("names.txt");
            pairs = ss.getPairs();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSize() {
        Assert.assertTrue("Sizes do not match", pairs.size() == ss.getPeople().size());
        Assert.assertTrue("Pairings do not contain all people", pairs.keySet().containsAll(ss.getPeople()));
    }

    @Test
    public void noFamilies() {
        Map<String, String> families = new HashMap<>();
        families.put("Jeff", "Jerry");
        families.put("Jerry", "Jeff");

        for(Map.Entry<String, String> e : families.entrySet()) {
            String s = e.getKey();
            Assert.assertFalse("Should not have family in Secret Santa", pairs.get(s).equals(e.getValue()));
        }
    }
}
