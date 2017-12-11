package io.github.nikmang.nov3017;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PyramidTest {

    Pyramid small;
    Pyramid med;
    Pyramid large;

    @Before
    void createPyramids() {
        try {
            small = new Pyramid("small.txt");
            med = new Pyramid("medium.txt");
            large = new Pyramid("large.txt");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testPyramid() {
        Assert.assertEquals(16, small.getMin());
        Assert.assertEquals(447, med.getMin());
        Assert.assertEquals(130572, large.getMin());
    }
}
