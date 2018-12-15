package io.github.nikmang.dec0918;

import java.util.Arrays;

/**
 * @author Nik Gil
 * Date: 9/12/2018
 */
public class Board {
    public static final char EMPTY_SPACE = '.';

    private char[][] board;

    public Board() {
        board = new char[3][3];

        for (char[] arr : board) {
            Arrays.fill(arr, EMPTY_SPACE);
        }
    }

    public boolean hasMovesLeft() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.')
                    return true;
            }
        }

        return false;
    }

    public boolean move(int x, int y, char c) {
        if(x < 0 || x > 2)
            return false;
        if(y < 0 || y > 2)
            return false;

        if (c == EMPTY_SPACE || board[x][y] == EMPTY_SPACE) {
            board[x][y] = c;
            return true;
        }

        return false;
    }

    public char getWinner() {
        //check rows
        for(int x=0; x<3; x++) {
            if((board[x][1] != EMPTY_SPACE) &&
                    board[x][0] == board[x][1] && board[x][1] == board[x][2])
                return board[x][1];
        }

        //check cols
        for(int y=0; y<3; y++) {
            if((board[1][y] != EMPTY_SPACE) &&
                    board[0][y] == board[1][y] && board[1][y] == board[2][y])
                return board[1][y];
        }

        //check diagonals
        if(board[1][1] != EMPTY_SPACE) {
            if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
                return board[1][1];

            if(board[0][2] == board[1][1] && board[1][1] == board[2][0])
                return board[1][1];
        }

        return EMPTY_SPACE;
    }

    public void printBoard() {
        for(int x=0; x<3; x++) {
            for(int y=0; y<3; y++) {
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
    }
}
