package io.github.nikmang.dec1717;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/3qjnil/20151028_challenge_238_intermediate_fallout/?st=jbazcisp&sh=6865cb8b
 * Difficulty: Intermediate
 */
public class Fallout {

    private List<String> words;
    private String chosen;

    public Fallout(String wordLoc) throws URISyntaxException, IOException {
        this.words = Files.readAllLines(Paths.get(Fallout.class.getResource(wordLoc).toURI()));
        this.chosen = "";
    }

    private List<String> setupWords(int diff) {
        List<String> subset = new ArrayList<>();
        Random r = new Random();

        //Random formulas that have probably better solutions
        int length = Math.min(15, Math.max(4, r.nextInt(15)+diff*2));
        int options = Math.min(15, Math.max(5, r.nextInt(15)+diff));

        while(chosen.equals("")) {
            String temp = words.get(r.nextInt(words.size()));

            if(temp.length() == length) {
                chosen = temp;
                subset.add(temp);
            }
        }

        while(subset.size() < options) {
            String temp = words.get(r.nextInt(words.size()));

            if(temp.length() != length)
                continue;
            if(!subset.contains(temp)) {
                subset.add(temp);
            }
        }

        return subset;
    }

    private int equalChars(String input, String rightword) {
        int similar = 0;

        for(int i=0; i<input.length(); i++) {
            if(i > rightword.length()) {
                break;
            }

            if(input.charAt(i) == rightword.charAt(i))
                similar++;
        }

        return similar;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        int lives = 4;
        int diff;

        do {
            System.out.print("Difficulty (1-5)? ");
            diff = sc.nextInt();

        } while(diff < 1 || diff > 5);

        List<String> words = setupWords(diff);

        words.forEach(s -> System.out.println(s.toUpperCase()));

        boolean win = false;

        while(lives > 0 && !win) {
            System.out.print(String.format("Guess (%d left)? ", lives));
            String input = sc.next();

            int similarity = equalChars(input.toLowerCase(), chosen);

            if(similarity == chosen.length())
                win = true;

            lives--;
            System.out.println(String.format("%d/%d correct", similarity, chosen.length()));
        }

        if(win)
            System.out.println("You win!");
        else
            System.out.println("You lose!");

        System.out.print("Play again? ");

        if(sc.next().equalsIgnoreCase("Y"))
            play();
        else
            System.exit(0);
    }
}
