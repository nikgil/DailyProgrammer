package io.github.nikmang.nov2617;


import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/5e4mde/20161121_challenge_293_easy_defusing_the_bomb/
 * Difficulty: Medium
 *
 * Test Cases [Output]:
 * white red green white [Bomb defused]
 * white orange green white [Boom]
 *
 * TODO Personal Bonus:
 * make a game that user has to defuse bomb that computer specifies
 */
public class Defusal {

    private static final Map<Wire, EnumSet<Wire>> invalid;
    private Wire currentState;

    static {
        Map<Wire, EnumSet<Wire>> temp = new HashMap<>();

        temp.put(Wire.WHITE, EnumSet.of(Wire.WHITE, Wire.BLACK));
        temp.put(Wire.RED, EnumSet.of(Wire.RED, Wire.WHITE, Wire.BLACK, Wire.ORANGE, Wire.PURPLE));
        temp.put(Wire.BLACK, EnumSet.of(Wire.WHITE, Wire.GREEN, Wire.ORANGE));
        temp.put(Wire.ORANGE, EnumSet.of(Wire.WHITE, Wire.ORANGE, Wire.PURPLE, Wire.GREEN));
        temp.put(Wire.GREEN, EnumSet.of(Wire.RED, Wire.BLACK, Wire.PURPLE, Wire.GREEN));
        temp.put(Wire.PURPLE, EnumSet.of(Wire.WHITE, Wire.ORANGE, Wire.PURPLE, Wire.GREEN));

        invalid = Collections.unmodifiableMap(temp);
    }

    private enum Wire {
        WHITE,
        BLACK,
        RED,
        ORANGE,
        GREEN,
        PURPLE;
    }

    //pretense that wires are all spelled correctly & wires is greater than 0 length array
    public String solve(String[] wires) {
        assert wires != null && wires.length > 0;

        currentState = Wire.valueOf(wires[0].toUpperCase());

        for(int i=1; i<wires.length; i++) {
            Wire next = Wire.valueOf(wires[i].toUpperCase());

            if(invalid.get(currentState).contains(next))
                return "Boom";

            currentState = next;
        }

        return  "Bomb has been defused";
    }

    @Test
    void testSolve() {
        Assert.assertEquals("Bomb has been defused", this.solve(new String[]{"white","red","green","white"}));
        Assert.assertEquals("Boom", this.solve(new String[]{"white","orange","green","white"}));
    }
}
