package io.github.nikmang.dec2517;

import java.util.*;

/**
 * Author: nint22
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/1t0r09/121613_challenge_145_easy_tree_generation/
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * 3 = # (tree with stump = and leaves #, going up to 1)
 *
 */
public class XmasTree {

    private int base;
    private String baseType, tree;

    public XmasTree(int base, String baseType, String tree) {
        assert base >= 3 && base <= 21;

        this.base = base;
        this. baseType = baseType;
        this.tree = tree;
    }

    public Stack<String[]> getTreeArray() {
        Stack<String[]> arr = new Stack<>();
        int middle = base/2;

        String[] baseLevel = new String[base];

        for(int i=0; i<base; i++) {
            baseLevel[i] = " ";
        }

        baseLevel[middle-1] = baseType;
        baseLevel[middle] = baseType;
        baseLevel[middle+1] = baseType;

        arr.add(baseLevel);

        for(int i=middle; i>=0; i--) {
            String[] level = new String[base];
            level[middle] = tree;

            for(int j=i; j>0; j--) {
                level[j+middle] = tree;
                level[middle-j] = tree;
            }

            for(int j=0; j<base; j++) {
                if(level[j] == null)
                    level[j] = " ";
            }

            arr.add(level);
        }

        return arr;
    }
}
