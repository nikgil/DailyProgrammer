package io.github.nikmang.dec0517;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Nik
 * Link: N/A
 * Difficulty: Easy
 *
 * Explanation:
 * In a given array, find the longest contiguous subarray that in sum is less than a given value. Return its length
 *
 * Test Cases {Output}:
 * [1,2,3] 1 {0}
 * [7,3,2,5,6] 11 {3}
 */
public class MinSubArray {

    private int[] arr;
    private int x;

    MinSubArray(int[] arr, int x) {
        this.arr = arr;
        this.x = x;
    }

    int getLength() {
        Queue<Integer> nums = new LinkedList<>();
        int sum = 0;
        int length = 0;

        for (int i : arr) {
            sum += i;
            nums.add(i);

            if (sum < x) {
                if(length < nums.size()) {
                    length = nums.size();
                }
            } else {
                while(sum > x) {
                    sum -= nums.poll();
                }
            }
        }

        return length;
    }
}
