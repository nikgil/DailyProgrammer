package io.github.nikmang.nov2417;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author: MasterAgent47
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7eh6k8/20171121_challenge_341_easy_repeating_numbers/?st=jacwnqpy&sh=7b1ad93c
 * Difficulty: Easy
 *
 * Test Cases [Output]:
 * 82156821568221 [8215682:2 821568:2 215682:2 82156:2 21568:2 15682:2 8215:2 2156:2 1568:2 5682:2 821:2 215:2 156:2 568:2 682:2 82:3 21:3 15:2 56:2 68:2]
 * 11111011110111011 [11110111:2 1111011:2 1110111:2 111101:2 111011:3 110111:2 11110:2 11101:3 11011:3 10111:2 1111:3 1110:3 1101:3 1011:3 0111:2 111:6 110:3 101:3 011:3 11:10 10:3 01:3]
 * 98778912332145 [0]
 * 124489903108444899 [44899:2 4489:2 4899:2 448:2 489:2 899:2 44:3 48:2 89:2 99:2]
 */
public class RepeatNums {

    private String num;
    private Map<String, Integer> appears;

    public RepeatNums() {
        appears = new HashMap<>();
    }

    private Set<String> getSubstring(int size) {
        Set<String> subs = new HashSet<>();

        for(int i=0; i<num.length()-size; i++) {
            subs.add(num.substring(i,i+size));
        }
        return subs;
    }

    private int getAppears(String s) {
        int index = 0;
        int count = 0;

        while(index != -1) {
            index = num.indexOf(s,index);

            if(index != -1) {
                count++;
                index++;
            }
        }

        return count;
    }

    public String run(String num) {
        appears.clear();
        this.num = num;

        for(int i=2; i<num.length(); i++) {
            Set<String> strings = getSubstring(i);

            for(String s : strings) {
                int appear = getAppears(s);

                if(appear > 1)
                    appears.put(s,appear);
            }
        }

        return this.toString();
    }

    @Override
    public String toString() {
        if(appears.isEmpty())
            return "0";

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry : appears.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(" ");
        }

        return sb.toString().trim();
    }
}
