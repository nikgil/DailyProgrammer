package io.github.nikmang.dec0617;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7btzrw/20171108_challenge_339_intermediate_a_car_renting/?st=jav9rtm6&sh=5f9a0026
 * Difficulty: Medium
 *
 * Test Cases [Output]:
 * 10
 * 1 12 5 12 13 40 30 22 70 19
 * 23 10 10 29 25 66 35 33 100 65
 *
 * [5
 * (5,10)(13,25)(30,35)(40,66)(70,100)]
 */
public class CarRent {

    List<Tuple> rentDays;

    //Skipping boilerplate parsing code
    public CarRent(int[] arr1, int[] arr2) {
        rentDays = new ArrayList<>();

        for(int i=0; i<arr1.length; i++) {
            rentDays.add(new Tuple(arr1[i], arr2[i]));
        }

        Collections.sort(rentDays);
    }

    public Stack<Tuple> getMaxReturn() {
        Stack<Tuple> rent = new Stack<>();
        Stack<Tuple> max = rent;

        for(int i=0; i<rentDays.size(); i++) {
            rent.add(rentDays.get(i));

            for(int j=i+1; j<rentDays.size(); j++) {
                Tuple next = rentDays.get(j);

                if(next.k > rent.peek().v) {
                    rent.add(next);
                }
            }

            if(rent.size() > max.size()) {
                max = rent;
            }
            rent = new Stack<>();
        }

        return max;
    }
}
