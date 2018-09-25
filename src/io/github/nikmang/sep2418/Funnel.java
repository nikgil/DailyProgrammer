package io.github.nikmang.sep2418;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Cosmologicon
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/99d24u/20180822_challenge_366_intermediate_word_funnel_2/
 * Difficulty: Intermediate
 * <p>
 * Test Cases [Output]:
 * gnash [4]
 * princesses [9]
 * turntables [5]
 * implosive [5]
 * programmer [2]
 */
public class Funnel {

    public static void main(String[] args) throws IOException {
        new Funnel(true);
    }
    private TreeNode root;

    public Funnel(boolean bonuses) throws IOException {
        root = new TreeNode();

        List<String> l = Files.readAllLines(Paths.get("src", "io", "github", "nikmang", "sep2418",
                "wordlist.txt"));

        for (String s : l) {
            TreeNode itr = root;

            for (char c : s.toCharArray()) {
                TreeNode next = itr.map.get(c);

                if (next == null) {
                    next = new TreeNode();
                    itr.map.put(c, next);
                }

                itr = next;
            }

            itr.isWord = true;
        }

        if (bonuses)
            for (String s : l) {
                if (getMax(s, false) == 10) {
                    System.out.println(s + " bonus1");
                }

                if(getMax(s, true) == 12) {
                    System.out.println(s + " bonus2");
                }
            }
    }

    int getMax(String word, boolean twoChars) {
        StringBuilder sb = new StringBuilder(word);

        int max = 0;

        if (!isWord(word))
            return max;

        for (int i = 0; i < word.length(); i++) {
            sb.deleteCharAt(i);

            max = Math.max(max, getMax(sb.toString(), twoChars));

            sb.insert(i, word.toCharArray()[i]);
        }

        if (twoChars)
            for (int i = 0; i < word.length() - 1; i++) {
                for (int j = 0; j < word.length(); j++) {
                    if (j == i)
                        continue;

                    if(j < i) {
                        sb.deleteCharAt(i);
                        sb.deleteCharAt(j);
                        max = Math.max(max, getMax(sb.toString(), twoChars));
                        sb.insert(j, word.toCharArray()[j]);
                        sb.insert(i, word.toCharArray()[i]);
                    } else {
                        sb.deleteCharAt(i);
                        sb.deleteCharAt(j - 1);
                        max = Math.max(max, getMax(sb.toString(), twoChars));
                        sb.insert(j - 1, word.toCharArray()[j]);
                        sb.insert(i, word.toCharArray()[i]);
                    }
                }
            }

        return 1 + max;
    }

    private boolean isWord(String s) {
        TreeNode node = root;

        for (char c : s.toCharArray()) {
            node = node.map.get(c);
            if (node == null)
                return false;
        }

        return node.isWord;
    }

    static class TreeNode {
        Map<Character, TreeNode> map;
        boolean isWord;

        TreeNode() {
            map = new HashMap<>();
            isWord = false;
        }
    }
}
