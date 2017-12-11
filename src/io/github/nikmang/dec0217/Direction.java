package io.github.nikmang.dec0217;

/**
 * Created by Nik on 12/3/2017.
 */
public enum Direction {

    HOME(0,0),
    NORTH(0,-1),
    SOUTH(0,1),
    EAST(1,0),
    WEST(-1,0),
    NORTH_EAST(1,-1),
    NORTH_WEST(-1,-1),
    SOUTH_EAST(1,1),
    SOUTH_WEST(-1,1);

    int x, y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
