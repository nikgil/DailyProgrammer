package io.github.nikmang.dec2317;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Steve132
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/z3a4y/8302012_challenge_93_easy_twoway_morse_code/
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * ... --- ... -> sos
 * sos -> ... --- ...
 *
 * Note: Tried to do this in a tree method. Adding worked (and translating TO morse) and result would be O(logn).
 * Problem with translate back it could be O(n) because of needing to traverse potentially ever node.
 * So I added a map for letters.
 */
public class MorseTree {
    public static final MorseTree MORSE_TREE = new MorseTree();
    private Map<Character, String> translations = new HashMap<>();

    private Node mainNode;

    private MorseTree() {
        mainNode = new Node(' ');

        //Letters
        translations.put('A', ".-");
        translations.put('B', "-...");
        translations.put('C', "-.-.");
        translations.put('D', "-..");
        translations.put('E', ".");
        translations.put('F', "..-.");
        translations.put('G', "--.");
        translations.put('H', "....");
        translations.put('I', "..");
        translations.put('J', ".---");
        translations.put('K', "-.-");
        translations.put('L', ".-..");
        translations.put('M', "--");
        translations.put('N', "-.");
        translations.put('O', "---");
        translations.put('P', ".--.");
        translations.put('Q', "--.-");
        translations.put('R', ".-.");
        translations.put('S', "...");
        translations.put('T', "-");
        translations.put('U', "..-");
        translations.put('V', "...-");
        translations.put('W', ".--");
        translations.put('X', "-..-");
        translations.put('Y', "-.--");
        translations.put('Z', "--..");

        //Numbers
        translations.put('1', ".----");
        translations.put('2', "..---");
        translations.put('3', "...--");
        translations.put('4', "....-");
        translations.put('5', ".....");
        translations.put('6', "-....");
        translations.put('7', "--...");
        translations.put('8', "---..");
        translations.put('9', "----.");
        translations.put('0', "-----");

        //Misc
        translations.put(',', "--..--");
        translations.put('.', ".-.-.-");
        translations.put('?', "..--..");
        translations.put('/', "-..-.");
        translations.put('-', "-....-");
        translations.put('(', "-.--.-"); translations.put(')', "-.--.-");

        for(Map.Entry<Character, String> entry : translations.entrySet()) {
            if(entry.getKey() == ')')
                continue;

            addChar(mainNode, entry.getKey(), entry.getValue().toCharArray());
        }
    }

    private void addChar(Node n, char c, char[] path) {
        assert path.length > 0;

        if(path.length == 1) {
            if(path[0] == '-') {
                if(n.dash == null)
                    n.dash = new Node(c);
                else
                    n.dash.value = c;
            } else {
                if(n.dot == null)
                    n.dot = new Node(c);
                else
                    n.dot.value = c;
            }

            return;
        }

        Node next;

        if(path[0] == '-') {
            if(n.dash == null)
                n.dash = new Node(' ');
            next = n.dash;
        } else {
            if(n.dot == null)
                n.dot = new Node(' ');
            next = n.dot;
        }

        addChar(next, c, Arrays.copyOfRange(path, 1, path.length));
    }

    public String getWord(String morse) {
        StringBuilder sb = new StringBuilder();
        String[] arr = morse.split("\\s");

        boolean openPar = false;

        for(String s : arr) {
            char c = getLetter(s.toCharArray());

            if(c == '(') {
                if(openPar) {
                    c = ')';
                }

                openPar = !openPar;
            }

            sb.append(c);
        }

        return sb.toString();
    }

    public String getMorse(String normal) {
        String[] words = normal.split("\\s");
        StringBuilder sb = new StringBuilder();

        for(String word : words) {
            StringBuilder sb2 = new StringBuilder();

            for(char c : word.toCharArray()) {
                sb2.append(translations.get(c));
            }

            sb.append(sb2).append(" ");
        }

        return sb.toString().trim();
    }
    public char getLetter(char[] path) {
        return getLetter(mainNode, path);
    }

    private char getLetter(Node n, char[] path) {
        assert path.length > 0;

        if(path.length == 1)
            return path[0] == '-' ? n.dash.value : n.dot.value;

        if(path[0] == '-')
            return getLetter(n.dash, Arrays.copyOfRange(path, 1, path.length));

        return getLetter(n.dot, Arrays.copyOfRange(path, 1, path.length));
    }

    class Node {
        private char value;
        private Node dash, dot;

        Node(char value) {
            this.value = value;
            this.dash = null;
            this.dot = null;
        }

        public char getValue() {
            return value;
        }

        public Node getDash() {
            return dash;
        }

        public Node getDot() {
            return dot;
        }
    }
}
