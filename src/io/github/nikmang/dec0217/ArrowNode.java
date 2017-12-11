package io.github.nikmang.dec0217;

/**
 * Created by Nik on 12/3/2017.
 */
public class ArrowNode {

    ArrowNode parent;
    int x, y;
    Direction direction;

    public ArrowNode(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.parent = null; //Will set this as I go along
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        return 37* (x^y);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof ArrowNode))
            return false;

        ArrowNode node = (ArrowNode) o;

        if(node.x == this.x && node.y == this.y)
            return true;

        return false;
    }
}
