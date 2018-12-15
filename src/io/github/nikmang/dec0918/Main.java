package io.github.nikmang.dec0918;

import java.util.Scanner;

/**
 * @author Nik Gil
 * Date: 9/12/2018
 */
public class Main {

    private static char player;
    private static char enemy;

    public static void main(String[] args) {
        boolean replay;
        Scanner sc = new Scanner(System.in);

        do {
            Board b = new Board();
            System.out.print("Do you want to go first (Y/N)?");
            if(sc.nextLine().equalsIgnoreCase("Y")) {
                player = 'X';
                enemy = 'O';
            } else {
                player = 'O';
                enemy = 'X';

                int[] arr = bestMove(b, enemy);
                b.move(arr[0], arr[1], enemy);
            }

            b.printBoard();

            while(b.getWinner() == Board.EMPTY_SPACE && b.hasMovesLeft()) {
                //I am going to pretend they don't enter invalid things
                System.out.print("Enter x pos: ");
                int x = sc.nextInt();
                System.out.print("Enter y pos: ");
                int y = sc.nextInt();

                if(b.move(x,y,player)) {
                    b.printBoard();
                    System.out.println("=======");
                    int[] arr = bestMove(b, enemy);
                    b.move(arr[0], arr[1], enemy);
                    b.printBoard();
                }
            }

            System.out.print("Do you want to play again (Y/N)? ");
            replay = sc.nextLine().equalsIgnoreCase("Y");
        } while(replay);
    }

    private static int minimax(Board b, int depth, boolean isPlayer) {
        int score = getScore(b);

        if(score != 0)
            return score - (isPlayer ? depth : -depth);

        if(!b.hasMovesLeft())
            return 0;

        if(isPlayer) {
            int maxScore = Integer.MIN_VALUE;

            for(int x=0; x<3; x++) {
                for(int y=0; y<3; y++) {
                    if(b.move(x,y, player)) {
                        int max = minimax(b, depth+1, false);

                        maxScore = Math.max(max, maxScore);

                        b.move(x,y,Board.EMPTY_SPACE);
                    }
                }
            }

            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;

            for(int x=0; x<3; x++) {
                for(int y=0; y<3; y++) {
                    if(b.move(x,y, player)) {
                        int max = minimax(b, depth+1, true);

                        minScore = Math.min(max, minScore);

                        b.move(x,y,Board.EMPTY_SPACE);
                    }
                }
            }

            return minScore;
        }
    }

    private static int[] bestMove(Board b, char target) {
        int[] arr = new int[0];
        int maxScore = Integer.MIN_VALUE;

        for(int x=0; x<3; x++) {
            for(int y=0; y<3; y++) {
                if(b.move(x,y, target)) {
                    int max = minimax(b, 0, true);

                    if(maxScore < max) {
                        maxScore = max;
                        arr = new int[]{x,y};
                    }

                    b.move(x,y,Board.EMPTY_SPACE);
                }
            }
        }

        return arr;
    }

    private static int getScore(Board board) {
        char winner = board.getWinner();

        if(winner == player)
            return -100;
        if(winner == enemy)
            return 100;

        return 0;
    }
}
