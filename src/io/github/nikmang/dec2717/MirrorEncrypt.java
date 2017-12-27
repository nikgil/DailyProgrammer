package io.github.nikmang.dec2717;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:  fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/4m3ddb/20160601_challenge_269_intermediate_mirror/
 * Difficulty: Intermediate
 *
 * Test Cases [Output]:
 * TpnQSjdmZdpoohd [DailyProgrammer] (encryption key given in test case file)
 */
public class MirrorEncrypt {
    Map<Character, int[]> keys;
    char[][] map;

    public MirrorEncrypt(char[][] mirrors) {
        keys = new HashMap<>(); //map is purely for quicker retrieval of initial letter
                                //saved as y,x (or column, row)
        this.map = new char[15][15];

        for(int i=0; i<13; i++) {
            keys.put((char)(97+i), new int[]{0, i+1}); //lowercase a-m
            this.map[0][i+1] = (char)(97+i);

            keys.put((char)(110+i), new int[]{i+1, 14}); //lowercase n-z
            this.map[i+1][14] = (char)(110+i);

            keys.put((char)(65+i), new int[]{i+1, 0}); //uppercase A-M
            this.map[i+1][0] = (char)(65+i);

            keys.put((char)(78+i), new int[]{14, i+1}); //uppercase N-Z
            this.map[14][i+1] = (char)(78+i);
        }

        for(int i=0; i<mirrors.length; i++) {
            System.arraycopy(mirrors[i], 0, this.map[i + 1], 1, mirrors[i].length);
        }

        /*for(char[] row : map) {
            for(char c : row) {
                System.out.print(c + " ");
            }

            System.out.println();
        }*/
    }

    public String translateWord(String word) {
        char[] letters = new char[word.length()];

        for(int i=0; i<letters.length; i++) {
            char c = word.toCharArray()[i];

            int[] xy = keys.get(c);

            if(xy[0] == 0) {
                letters[i] = moveDown(xy[0], xy[1]);
            } else if(xy[0] == 14) {
                letters[i] = moveUp(xy[0], xy[1]);
            } else if(xy[1] == 0) {
                letters[i] = moveRight(xy[0], xy[1]);
            } else {
                letters[i] = moveLeft(xy[0], xy[1]);
            }
        }

        return String.valueOf(letters);
    }

    private char moveLeft(int column, int row) {
        row--;
        while(map[column][row] == ' ') {
            row--;
        }

        if(map[column][row] == '\\') {
            return moveUp(column,row);
        }

        if(map[column][row] == '/') {
            return moveDown(column, row);
        }

        return map[column][row];
    }

    private char moveRight(int column, int row) {
        row++;
        while(map[column][row] == ' ') {
            row++;
        }

        if(map[column][row] == '\\') {
            return moveDown(column,row);
        }

        if(map[column][row] == '/') {
            return moveUp(column,row);
        }

        return map[column][row];
    }

    private char moveUp(int column, int row) {
        column--;
        while(map[column][row] == ' ') {
            column--;
        }

        if(map[column][row] == '\\') {
            return moveLeft(column,row);
        }

        if(map[column][row] == '/') {
            return moveRight(column,row);
        }

        return map[column][row];
    }

    private char moveDown(int column, int row) {
        column++;
        while(map[column][row] == ' ') {
            column++;
        }

        if(map[column][row] == '\\') {
            return moveRight(column,row);
        }

        if(map[column][row] == '/') {
            return moveLeft(column,row);
        }

        return map[column][row];
    }
}
