package io.github.nikmang.nov2517;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MinefieldTests {

    private static  List<String> testMapLarge, testMapSmall;
    private static Minefield testFieldLarge, testFieldSmall;

    @BeforeClass
    public static void setup() {
        testMapLarge = Arrays.asList("+++++++++++",
                                     "+0000000000",
                                     "+000000*00+",
                                     "+000000000+",
                                     "+000*00*00+",
                                     "+000000000+",
                                     "M000*00000+",
                                     "+++++++++++");
        
        testMapSmall = Arrays.asList(   "+++++++",
                                        "+00*000",
                                        "+00M*0+",
                                        "+00*00+",
                                        "+00000+",
                                        "+++++++");

        try {
            testFieldLarge = new Minefield(testMapLarge);
            testFieldSmall = new Minefield(testMapSmall);
        } catch (MinefieldException e) {
        }
    }

    @Test
    public void testMapParse() {
        //TODO: maps with invalid chars & corner exits
        List<String> map2 = Arrays.asList(  "+++++++++++",
                                            "+000000000+",
                                            "+000000*00+",
                                            "+000000000+",
                                            "+000*00*00+",
                                            "+000000000+",
                                            "M000*00000+",
                                            "+++++++++++");

        List<String> map3 = Arrays.asList(  "+++++++++++",
                                            "+0000000000",
                                            "+000000*00+",
                                            "+000000000+",
                                            "+000*00*00+",
                                            "+000000000+",
                                            "+000*00000+",
                                            "+++++++++++");

        boolean isSafe = true;

        try {
            new Minefield(testMapLarge);
            new Minefield(testMapSmall);
        } catch (MinefieldException e) {
            isSafe = false;
        }

        Assert.assertTrue(isSafe);
        isSafe = true;

        try {
            new Minefield(map2);
        } catch (MinefieldException e) {
            isSafe = false;
        }

        Assert.assertFalse(isSafe);
        isSafe = true;

        try {
            new Minefield(map3);
        } catch (MinefieldException e) {
            isSafe = false;
        }

        Assert.assertFalse(isSafe);
    }

    @Test
    public void testBotTraversal() {
        //Small test
        boolean gameS = testFieldSmall.runGame("IOSSEEENNNE-");
        Assert.assertTrue(gameS);
        testFieldSmall.reset();

        boolean gameS2 = testFieldSmall.runGame("IOSSEEEENNONE-");
        Assert.assertTrue(gameS2);

        //Large test
        boolean game1 = testFieldLarge.runGame("IENENNNNEEEEEEEE-");
        Assert.assertTrue(game1);
        testFieldLarge.reset();

        boolean game2 = testFieldLarge.runGame("IENE");
        Assert.assertFalse(game2);
        testFieldLarge.reset();

        boolean game3 = testFieldLarge.runGame("IENENNNNEEEEEEEE");
        Assert.assertFalse(game3);
        testFieldLarge.reset();

        boolean game4 = testFieldLarge.runGame("ENENNNNEEEEEEEE-");
        Assert.assertFalse(game4);
        testFieldLarge.reset();

        boolean game5 = testFieldLarge.runGame("LENENNNNEEEEEEEE-");
        Assert.assertFalse(game5);
        testFieldLarge.reset();
    }
}
