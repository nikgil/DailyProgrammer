package io.github.nikmang.dec0417;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nik on 12/4/2017.
 */
public class PathfinderTest {

    @Test
    public void testDijkstra() {
        Pathfinder p = new Pathfinder();
        String s = p.dijkstra();

        assertEquals("(0,2)(1,3)(2,3)(3,2)(4,3)(5,4)(6,3)(7,4)(8,3)(9,2)(10,3)(11,2)(12,2)(13,2)(14,1)(15,0)(16,0)(17,0)(18,1)(17,2)(16,2)", s);
        System.out.println(p.iterations);
    }

    @Test
    public void testAStar() {
        Pathfinder p = new Pathfinder();
        String s = p.a_star();

        assertEquals("(0,2)(1,3)(2,3)(3,2)(4,3)(5,4)(6,3)(7,4)(8,3)(9,2)(10,3)(11,2)(12,2)(13,2)(14,1)(15,0)(16,0)(17,0)(18,1)(17,2)(16,2)", s);
        System.out.println(p.iterations);
    }
}
