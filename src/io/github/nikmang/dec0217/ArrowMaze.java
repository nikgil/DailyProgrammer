package io.github.nikmang.dec0217;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/6rb98p/20170803_challenge_325_intermediate_arrow_maze/?st=jar61gfm&sh=06826015
 * Difficulty: Medium
 *
 * Test Cases [Output]:
 * Start at 2,0
 *  e se se sw  s
 *  s nw nw  n  w
 * ne  s  h  e sw
 * se  n  w ne sw
 * ne nw nw  n  n
 * [(2,0)(3,1)(3,0)(1,2)(1,3)(1,1)(0,0)(4,0)(4,1)(0,1)(0,4)(2,2)]
 */
public class ArrowMaze {

    private ArrowNode[][] map;
    private Queue<ArrowNode> open;
    private Set<ArrowNode> closed;

    public static void main(String[] args) {
        try {
            ArrowMaze maze = new ArrowMaze("map.txt", 0,0);

            ArrowNode home = maze.bfs();

            System.out.println(maze.drawPath(home));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
    //For custom mazes
    // Format is "E|N|SE"
    //Lines are greater than 0 and is square
    ArrowMaze(String fileName, int startX, int startY) throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Paths.get(ArrowMaze.class.getResource(fileName).toURI()));

        assert !lines.isEmpty();

        map = new ArrowNode[lines.size()][lines.size()];

        for(int i= 0; i<lines.size(); i++) {
            String line = lines.get(i);
            String[] squares = line.split("\\|");

            for(int j=0; j<squares.length; j++) {
                Direction d;

                switch(squares[j]) {
                    case "N":
                        d = Direction.NORTH;
                        break;
                    case "E":
                        d = Direction.EAST;
                        break;
                    case "S":
                        d = Direction.SOUTH;
                        break;
                    case "W":
                        d = Direction.WEST;
                        break;
                    case "H":
                        d = Direction.HOME;
                        break;
                    case "SE":
                        d = Direction.SOUTH_EAST;
                        break;
                    case "SW":
                        d = Direction.SOUTH_WEST;
                        break;
                    case "NW":
                        d = Direction.NORTH_WEST;
                        break;
                    case "NE":
                        d = Direction.NORTH_EAST;
                        break;
                    default:
                        d = null;
                }

                ArrowNode node = new ArrowNode(j,i,d);

                map[i][j] = node;
            }
        }

        open = new LinkedList<>();
        closed = new HashSet<>();

        open.add(map[startY][startX]);
    }

    //Going to skip the boilerplate code of parsing everything and translating to nodes
    //And just do nodes
    ArrowMaze() {
        map = new ArrowNode[][]{{new ArrowNode(0,0, Direction.EAST), new ArrowNode(1,0, Direction.SOUTH_EAST), new ArrowNode(2,0, Direction.SOUTH_EAST),
                        new ArrowNode(3,0, Direction.SOUTH_WEST), new ArrowNode(4,0, Direction.SOUTH)},
                {new ArrowNode(0,1, Direction.SOUTH), new ArrowNode(1,1, Direction.NORTH_WEST), new ArrowNode(2,1, Direction.NORTH_WEST),
                        new ArrowNode(3,1, Direction.NORTH), new ArrowNode(4,1, Direction.WEST)},
                {new ArrowNode(0,2, Direction.NORTH_EAST), new ArrowNode(1,2, Direction.SOUTH), new ArrowNode(2,2, Direction.HOME),
                        new ArrowNode(3,2, Direction.EAST), new ArrowNode(4,2, Direction.SOUTH_WEST)},
                {new ArrowNode(0,3, Direction.SOUTH_EAST), new ArrowNode(1,3, Direction.NORTH), new ArrowNode(2,3, Direction.WEST),
                        new ArrowNode(3,3, Direction.NORTH_EAST), new ArrowNode(4,3, Direction.SOUTH_WEST)},
                {new ArrowNode(0,4, Direction.NORTH_EAST), new ArrowNode(1,4, Direction.NORTH_WEST), new ArrowNode(2,4, Direction.NORTH_WEST),
                        new ArrowNode(3,4, Direction.NORTH), new ArrowNode(4,4, Direction.NORTH)}};

        open = new LinkedList<>();
        closed = new HashSet<>();

        open.add(map[0][2]);
    }

    ArrowNode bfs() {
        while(!open.isEmpty()) {
            ArrowNode current = open.poll();

            if(current.direction == Direction.HOME)
                return current;


            int x = current.x;
            int y = current.y;

            x += current.direction.x;
            y += current.direction.y;

            while(isValid(x,y)) {
                ArrowNode n = map[y][x];

                if(!open.contains(n) && !closed.contains(n)) {
                    open.add(n);
                    n.parent = current;
                }

                x += current.direction.x;
                y += current.direction.y;
            }

            if(current.parent != null) {
                x = current.x;
                y = current.y;

                x += current.parent.direction.x;
                y += current.parent.direction.y;

                while(isValid(x,y)) {
                    ArrowNode n = map[y][x];

                    if(!open.contains(n) && !closed.contains(n)) {
                        open.add(n);
                        n.parent = current.parent;
                    }

                    x += current.parent.direction.x;
                    y += current.parent.direction.y;
                }
            }

            closed.add(current);
        }

        return null;
    }

    String drawPath(ArrowNode node) {
        StringBuilder sb = new StringBuilder();

        while(node != null) {
            sb.insert(0, node.toString());
            node = node.parent;
        }

        return sb.toString();
    }

    private boolean isValid(int x, int y) {
        if(y < 0 || y >= map.length)
            return false;

        if(x < 0 || x >= map[y].length)
            return false;

        return true;
    }
}
