package io.github.nikmang.nov2517;

/**
 * Created by Nik on 11/24/2017.
 */
public class MineBot {

    private final int startX, startY;

    private String[] map;
    private boolean running;
    private int x,y;

    public MineBot(String[] map, int x, int y) {
        this.map = map;
        this.x = this.startX = x;
        this.y = this.startY = y;

        this.running = false;
    }

    public void reset() {
        this.running = false;
        this.x = startX;
        this.y = startY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(char direction) {
        if(direction == 'I') {
            running = true;
            return;
        }

        if(!running)
            return;

        switch(direction) {
            case 'S':
                y++;

                if(y >= map.length || map[y].charAt(x) == '+' || map[y].charAt(x) == '*')
                    y--;
                break;
            case 'N':
                y--;

                if(y < 0 || map[y].charAt(x) == '+' || map[y].charAt(x) == '*')
                    y++;
                break;
            case 'E':
                x++;

                if(x >= map[y].length() || map[y].charAt(x) == '+' || map[y].charAt(x) == '*')
                    x--;
                break;
            case 'O': //Yes O, not W, but O
                x--;

                if(x < 0 || map[y].charAt(x) == '+' || map[y].charAt(x) == '*')
                    x++;
                break;
            case '-':
                running = false;
        }
    }

    public boolean isFree() {
        if(running)
            return false;

        if(x == startX && y == startY)
            return false;

        System.out.println(x + " " + y);
        //Doing in two separate ones for clarity
        if(y==0 || y==map.length-1)
            return true;

        if(x==0 || x==map[y].length()-1)
            return true;

        return false;
    }
}
