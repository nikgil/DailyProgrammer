package io.github.nikmang.sep2218;

import java.util.Arrays;
import java.util.List;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/8sjcl0/20180620_challenge_364_intermediate_the_ducci/
 * Difficulty: Medium
 */
public class Ducci {

    // Is this efficient? Hell no!
    // copy from one array to another, iterate through an n size list each time, Arrays.equals is also O(n) operation
    // in summary: this is O(n) but non-ammortized O(4n) with space complexity of O(n) as the stack grows recursively
    public static int getSteps(int[] tuple, List<int[]> prev) {
        int[] newArr = new int[tuple.length];
        int[] zeros = new int[tuple.length];

        if(Arrays.equals(tuple, zeros))
            return 1;

        for(int i=0; i<newArr.length-1; i++) {
            newArr[i] = Math.abs(tuple[i] - tuple[i+1]);
        }

        newArr[newArr.length-1] = Math.abs(tuple[0] - tuple[tuple.length-1]);

        if(Arrays.equals(newArr, zeros)) {
            return 2;
        }

        for(int[] arr : prev) {
            if(Arrays.equals(arr, newArr))
                return 2;
        }

        prev.add(newArr);

        return 1 + getSteps(newArr, prev);
    }


}
