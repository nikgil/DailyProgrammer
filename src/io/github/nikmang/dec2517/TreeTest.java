package io.github.nikmang.dec2517;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by Nik on 12/25/2017.
 */
public class TreeTest {

    @Test
    public void testTree() {
        XmasTree x1 = new XmasTree(21, "=", "+");

        Stack<String[]> tree = x1.getTreeArray();

        while (!tree.isEmpty()) {
            String[] branch = tree.pop();

            for (String s : branch)
                System.out.print(s);

            System.out.println("");
        }
    }
}
