package io.github.nikmang.sep2418;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Nik Gil
 * Date: 24/9/2018
 */
public class FunnelTest {

    @Test
    public void testFunnels() throws IOException {
        Funnel funnel = new Funnel(true);

        Assert.assertEquals(4, funnel.getMax("gnash", false));
        Assert.assertEquals(9, funnel.getMax("princesses", false));
        Assert.assertEquals(5, funnel.getMax("turntables", false));
        Assert.assertEquals(1, funnel.getMax("implosive", false));
        Assert.assertEquals(2, funnel.getMax("programmer", false));

        //Bonus 1: complecting
    }
}
