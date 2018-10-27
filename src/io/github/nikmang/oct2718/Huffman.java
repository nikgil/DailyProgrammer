package io.github.nikmang.oct2718;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Huffman encoding/decoding. Been meaning to do this for a long time.
 * 
 * @author Nik Gil
 * Date: 27/10/2018
 */
public class Huffman {


    public static void main(String[] args) {
        String s = "how much wood could a woodchuck chuck if a woodchuck could chuck wood";

        TreeNode encoder = generateTree(s);
        String encoded = encode(s, encoder);
        String decoded = decode(encoded, encoder);

        System.out.println(encoded);
        System.out.println(decoded);
    }

    //Generates a tree based on input string for encoding
    private static TreeNode generateTree(String s) {
        assert s.length() > 1;

        Map<Character, Integer> freqs = new HashMap<>();

        for (char c : s.toCharArray()) {
            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
        }

        Queue<TreeNode> q = new PriorityQueue<>(freqs.size(), new Comparator<TreeNode>() {
            @Override
            public int compare(final TreeNode o1, final TreeNode o2) {
                return o1.weight - o2.weight;
            }
        });

        q.addAll(freqs.keySet().stream().map(c -> new TreeNode(c, freqs.get(c))).collect(Collectors.toList()));

        /*
        How this works:
        - Takes the minimum 2 values by weight
        - joins them into a subtree
        - this continues until one value in queue remains - the main tree
         */
        while(q.size() > 1) {
            TreeNode l = q.poll();
            TreeNode r = q.poll();

            TreeNode root = new TreeNode(l.weight + r.weight);

            root.left = l;
            root.right = r;

            q.offer(root);
        }

        return q.peek();
    }

    // These are pretty self explanatory and the only part that may need mention is
    // that 1 = right, 0 = left
    private static String encode(String s, TreeNode root) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            sb.append(root.getPath(c));
        }

        return sb.toString();
    }

    private static String decode(String s, TreeNode root) {
        TreeNode tempRoot = root;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char dir = s.charAt(i);

            if (dir == '1')
                tempRoot = tempRoot.right;
            else
                tempRoot = tempRoot.left;

            if (tempRoot.val != null) {
                sb.append(tempRoot.val);
                tempRoot = root;
            }
        }

        return sb.toString();
    }

    private static class TreeNode {
        Character val;
        int weight;
        TreeNode left, right;

        TreeNode(int weight) {
            this.weight = weight;
        }

        TreeNode(char c, int weight) {
            this.val = c;
            this.weight = weight;
        }

        // These can easily be improved by adding a stringbuilder rather than
        // persistent concats and possible memoization for repeated characters
        public String getPath(char c) {
            if (val != null && c == val)
                return "";

            String l = null, r = null;

            if (left != null)
                l = left.getPath(c);

            if (right != null)
                r = right.getPath(c);

            if (l != null)
                return "0" + l;

            if (r != null)
                return "1" + r;

            return null;
        }
    }
}
