package io.github.nikmang.dec1517;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TestGame {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList("SP3F", "DP3O", "DR2F", "SP3H", "DG3O", "SR1H", "SG2O", "SP1F", "SP3O", "OR3O", "OR3H", "OR2H"),
                        Arrays.asList(new String[]{"SP3F", "SR1H", "SG2O"}, new String[]{"SP3F", "DG3O", "OR3H"}, new String[]{"SP3F", "SP3H", "SP3O"},
                                new String[]{"DR2F", "SR1H", "OR3O"}, new String[]{"DG3O", "SP1F", "OR2H"}, new String[]{"DG3O", "SP3O", "OR3O"})},

                {Arrays.asList("DP2H", "DP1F", "SR2F", "SP1O", "OG3F", "SP3H", "OR2O", "SG3O", "DG2H", "DR2H", "DR1O", "DR3O"),
                        Arrays.asList(new String[]{"DP1F", "SR2F", "OG3F"}, new String[]{"DP2H", "DG2H", "DR2H"}, new String[]{"DP1F", "DG2H", "DR3O"},
                                new String[]{"SR2F", "OR2O", "DR2H"}, new String[]{"SP1O", "OG3F", "DR2H"}, new String[]{"OG3F", "SP3H", "DR3O"})}
        });
    }

    List<String> input;
    List<String[]> outputs;

    public TestGame(List<String> input, List<String[]> outputs) {
        this.input = input;
        this.outputs = outputs;
    }

    @Test
    public void testRun() {
        SetGame game = new SetGame(input);

        List<String[]> results = game.getCombos();

        boolean isFound = false;

        for(String[] s : results) {
            for(String[] s2 : outputs) {
                if(Arrays.equals(s,s2)) {
                    isFound = true;
                    break;
                }
            }

            Assert.assertTrue("Array " + s[0] + " " + s[1] + " " + s[2] + " not found!", isFound);
        }
    }
}
