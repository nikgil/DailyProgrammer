package io.github.nikmang.nov2517;

import java.util.List;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7d4yoe/20171114_challenge_340_intermediate_walk_in_a/?st=jaeahkpj&sh=60dc67f8
 * Difficulty: Medium
 *
 * Test Cases:
 * Cases found in {@link MinefieldTests}
 * 1. Enter a minefield
 * 2. Letters for traversal
 *
 * Output: True/False if they made it out
 *
 * TODO: gif of moving through map
 */
public class Minefield {

    private String[] map;
    private MineBot bot;

    public Minefield(List<String> lines) throws MinefieldException {
        int[] startPos = this.parseMap(lines);

        this.bot = new MineBot(map, startPos[0], startPos[1]);
    }

    public int[] parseMap(List<String> lines) throws MinefieldException {
        assert lines != null && !lines.isEmpty();
        //Not testing line length but taking as pretence

        int[] startPos = new int[2]; //Because tuples aren't a thing here

        boolean containsBot = false;
        boolean containsExit = false;

        map = new String[lines.size()];

        //I will let invalid letters slide as they cause no real harm other than making the map look bad
        for (int i=0; i < map.length; i++) {
            String level = lines.get(i);

            int index = level.indexOf("M");

            if(index > -1) {
                if(containsBot) {
                    throw new MinefieldException("Minefield already contains bot");
                } else {
                    containsBot = true;
                    startPos[0] = index;
                    startPos[1] = i;
                }
            }

            map[i] = level;
        }

        //Need at least one exit
        int index0 = map[0].indexOf("0");
        int indexLast = map[map.length-1].indexOf("0");

        //I can still see some corner cases that can have problems with this but it should work
        if(index0 != -1) {
            if(index0 > 0 && index0 < map[0].length()-1)
                containsExit = true;
        }

        if(indexLast != -1 && !containsExit) {
            if(indexLast > 0 && indexLast < map[0].length()-1)
                containsExit = true;
        }


        if(!containsExit)
            for(int i=1; i<map.length-1; i++) {
                String level = map[i];

                if(level.indexOf("0")==0 || level.lastIndexOf("0")==level.length()-1) {
                    containsExit = true;
                    break;
                }
            }

        if (!containsBot || !containsExit)
            throw new MinefieldException("No bot or no exit found");

        return startPos;
    }

    public boolean runGame(String s) {
        for(char c : s.toCharArray())
            bot.move(c);

        return bot.isFree();
    }

    //Keeping this in case I add something later
    public void reset() {
        this.bot.reset();
    }
}
