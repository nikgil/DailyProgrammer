package io.github.nikmang.sep2318;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/8xzwl6/20180711_challenge_365_intermediate_sales/
 * Difficulty: Intermediate
 *
 * Test Cases [Output]: in {@link CommissionTests}
 */
public class Commission {

    public static long[] getCommission(double commission, String[] names, int[][] revenues, int[][] expenses) {
        Map<String, Double> map = new LinkedHashMap<>();

        for(int i=0; i<names.length; i++) {
            String name = names[i];

            if(!map.containsKey(name))
                map.put(name, 0D);

            for(int j=0; j<revenues.length; j++) {
                double profit =  (revenues[j][i] - expenses[j][i]);

                if(profit > 0)
                    map.put(name, map.get(name)+profit);
            }
        }

        long[] profits = new long[map.size()];
        int i=0;

        for(Double d : map.values()) {
            profits[i] = (long)Math.floor(d * commission);
            i++;
        }

        return profits;
    }
}
