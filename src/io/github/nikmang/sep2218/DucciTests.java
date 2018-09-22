package io.github.nikmang.sep2218;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Nik Gil
 * Date: 22/9/2018
 */
public class DucciTests {

    @Test
    public void testDuccis() {
        Assert.assertEquals(24, Ducci.getSteps(new int[]{0, 653, 1854, 4063}, new ArrayList<>()));
        Assert.assertEquals(23, Ducci.getSteps(new int[]{1, 5, 7, 9, 9}, new ArrayList<>()));
        Assert.assertEquals(3, Ducci.getSteps(new int[]{1, 2, 1, 2, 1, 0}, new ArrayList<>()));
        Assert.assertEquals(22, Ducci.getSteps(new int[]{10, 12, 41, 62, 31, 50}, new ArrayList<>()));
        Assert.assertEquals(30, Ducci.getSteps(new int[]{10, 12, 41, 62, 31}, new ArrayList<>()));
    }
}
