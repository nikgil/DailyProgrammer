package io.github.nikmang.nov2317;


import org.junit.Assert;
import org.junit.Test;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/6jr76h/20170627_challenge_321_easy_talking_clock/
 * Difficulty: Easy
 *
 * Test cases [Output]:
 * 00:00 [It's twelve am]
 * 01:30 [It's one thirty am]
 * 12:05 [It's twelve oh five pm]
 * 14:01 [It's two oh one pm]
 * 20:29 [It's eight twenty nine pm]
 * 21:00 [It's nine pm]
 */
public class Clock {

    String[] hours = {"twelve", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven"};
    String[] mins = {"oh", "teen", "twenty", "thirty", "fourty", "fifty"};

    /**
     * Precondition: valid time input (hh:mm)
     */
    public String getTime(String arg) {
        boolean pm = false;

        StringBuilder timeBuilder = new StringBuilder("It\'s ");
        String[] split = arg.split(":"); //Separate hour and mins

        int hr = Integer.parseInt(split[0]);

        if(hr>=12) {
            pm = true;
            hr %= 12;
        }

        timeBuilder.append(hours[hr]);

        //minutes
        if(!split[1].equals("00")) {
            timeBuilder.append(" ");
            int min = Integer.parseInt(split[1]);

            if(min < 10) {
                timeBuilder.append(mins[0]).append(" ").append(hours[min]);
            } else if(min == 10) {
                timeBuilder.append(hours[10]);
            } else if(min == 11) {
                timeBuilder.append("eleven");
            } else if(min == 12) {
                timeBuilder.append("twelve");
            } else if(min < 20) {
                timeBuilder.append(hours[min].replace(" ", "")).append(mins[1]);
            } else {
                timeBuilder.append(mins[min/10]);

                int submin = min%10;
                if(submin!=0)
                    timeBuilder.append(" ").append(hours[submin]);
            }
        }

        if(pm)
            timeBuilder.append(" pm");
        else
            timeBuilder.append(" am");

        return timeBuilder.toString();
    }

    @Test
    public void testNov2317() {
        Clock cl = new Clock();

        Assert.assertEquals("It's twelve am", cl.getTime("00:00"));
        Assert.assertEquals("It's one thirty am", cl.getTime("01:30"));
        Assert.assertEquals("It's twelve oh five pm", cl.getTime("12:05"));
        Assert.assertEquals("It's two oh one pm", cl.getTime("14:01"));
        Assert.assertEquals("It's eight twenty nine pm", cl.getTime("20:29"));
        Assert.assertEquals("It's nine pm", cl.getTime("21:00"));

    }
}
