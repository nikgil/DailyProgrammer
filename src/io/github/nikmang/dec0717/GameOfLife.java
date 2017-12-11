package io.github.nikmang.dec0717;

import java.util.Arrays;

/**
 * Author: Elite6809
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/271xyp/622014_challenge_165_easy_ascii_game_of_life/?st=jaw35rtu&sh=ec19c9d8
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * 7 10 10
 * ..........
 * ..........
 * ..#.......
 * ...#......
 * .###......
 * ..........
 * ..........
 * ..........
 * ..........
 * ..........
 *
 * [
 * ..........
 * ..........
 * ..........
 * ..........
 * ...#......
 * ....##....
 * ...##.....
 * ..........
 * ..........
 * ..........
 * ]
 */
public class GameOfLife {

    String[][] grid;

    public GameOfLife(String[][] grid) {
        this.grid = grid;
    }

    String[] getNeighbours(int i, int j) {
        String[] neighbours = new String[8];

        int top, bottom;
        int left, right;

        if(i == 0) {
            top = grid.length-1;
            bottom = i+1;
        } else {
            top = i-1;

            if(i == grid.length-1) {
                bottom = 0;
            } else {
                bottom = i+1;
            }
        }

        if(j == 0) {
            left = grid[i].length-1;
            right = j+1;
        } else {
            left = j-1;

            if(j == grid[i].length-1) {
                right = 0;
            } else {
                right = j+1;
            }
        }

        //All goes in a clockwise circle starting from top left
        //Top 3
        neighbours[0] = grid[top][left];
        neighbours[1] = grid[top][j];
        neighbours[2] = grid[top][right];

        //Right
        neighbours[3] = grid[i][right];

        //Bottom 3
        neighbours[4] = grid[bottom][right];
        neighbours[5] = grid[bottom][j];
        neighbours[6] = grid[bottom][left];

        //Left
        neighbours[7] = grid[i][left];
        return neighbours;
    }

    void step() {
        String[][] clone = new String[grid.length][];

        for(int i=0; i<grid.length; i++) {
            String[] row = grid[i];

            clone[i] = row.clone();
            for(int j=0; j<row.length; j++) {
                String s = row[j];

                if(s.equals("#")){
                    if(willDie(getNeighbours(i,j))) {
                        clone[i][j] = ".";
                    }
                } else {
                    if(backToLife(getNeighbours(i,j))) {
                        clone[i][j] = "#";
                    }
                }
            }
        }

        grid = clone;
    }

    boolean backToLife(String[] neighbours) {
        int alive = 0;

        for(String s : neighbours) {
            if(s.equals("#")) {
                alive++;
            }
        }

        return alive == 3;
    }

    boolean willDie(String[] neighbours) {
        int alive = 0;

        for(String s : neighbours) {
            if(s.equals("#")) {
                alive++;
            }
        }

        return alive < 2 || alive > 3;
    }

    void printMapString() {
        System.out.println("");
        for(String[] line : grid) {
            StringBuilder sb = new StringBuilder();

            for (String s : line) {
                sb.append(s);
            }

            System.out.println(sb);
        }
        System.out.println("");
    }
}
