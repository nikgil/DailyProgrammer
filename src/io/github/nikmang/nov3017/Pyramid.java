package io.github.nikmang.nov3017;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

/**
 * Author: i3aizey
 * Link: https:https://www.reddit.com/r/dailyprogrammer/comments/6vi9ro/170823_challenge_328_intermediate_pyramid_sliding/?st=japegu55&sh=ff3ac717
 * Difficulty: Medium
 *
 * Test Cases:
 * Found in separate files
 */
public class Pyramid {

    private List<String> lines;
    private Stack<int[]> pyramid;

    Pyramid(String resource) throws URISyntaxException, IOException {
        lines = Files.readAllLines(Paths.get(Pyramid.class.getResource(resource).toURI()));
        pyramid = new Stack<>();

        createPyramid();
    }

    private void createPyramid() {
        for (String s : lines) {
            String[] numsS = s.split("\\s");
            int[] nums = new int[numsS.length];

            for (int i = 0; i < numsS.length; i++) {
                nums[i] = Integer.parseInt(numsS[i]);
            }

            pyramid.push(nums);
        }
    }

    //For simplicity sake
    //Also an do a check if there is only 1/0 level(s)
    int getMin() {
        return getMin(pyramid.pop());
    }

    private int getMin(int[] arr) {
        int[] currentLevel = pyramid.pop();

        if(pyramid.isEmpty())
            return arr[0] < arr[1] ? currentLevel[0] + arr[0] : currentLevel[0] + arr[1];

        for(int i=0; i<currentLevel.length; i++) {
            currentLevel[i] += arr[i] < arr[i+1] ? arr[i] : arr[i+1];
        }

        return getMin(currentLevel);
    }
}