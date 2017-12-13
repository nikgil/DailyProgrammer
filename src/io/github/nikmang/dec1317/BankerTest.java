package io.github.nikmang.dec1317;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by Nik on 12/13/2017.
 */
public class BankerTest {

    @Test
    public void testNoSolution() {
        int[] allocated = {3,3,2};
        Process p0 = new Process("P0", new int[]{0,1,0}, new int[]{7,5,3});
        Process p2 = new Process("P2", new int[]{3,0,2}, new int[]{9,0,2});
        Process p4 = new Process("P4", new int[]{0,0,2}, new int[]{4,3,3});

        List<Process> path = Arrays.asList(p4,p0,p2);

        Banker banker = new Banker(allocated);
        banker.processes.addAll(path);

        //This also adds to inner object lists which is bad practice as the function contains a hidden side-effect
        List<Process> processes = banker.runProgram();

        Assert.assertEquals(0, processes.size());
    }


    @Test
    public void testBanker() {
        int[] allocated = {3,3,2};
        Process p0 = new Process("P0", new int[]{0,1,0}, new int[]{7,5,3});
        Process p1 = new Process("P1", new int[]{2,0,0}, new int[]{3,2,2});
        Process p2 = new Process("P2", new int[]{3,0,2}, new int[]{9,0,2});
        Process p3 = new Process("P3", new int[]{2,1,1}, new int[]{2,2,2});
        Process p4 = new Process("P4", new int[]{0,0,2}, new int[]{4,3,3});

        List<Process> path = Arrays.asList(p1,p4,p3,p0,p2);

        Banker banker = new Banker(allocated);
        banker.processes.addAll(path);

        //This also adds to inner object lists which is bad practice as the function contains a hidden side-effect
        banker.runProgram();
        Queue<Process> suggested = new LinkedList<>(path);

        boolean isValid = true;

        //really should not be doing this way in good design because of very relaxed mutability but just for the exercise
        while(isValid && suggested.size() > 1){
            Process current = suggested.poll();

            isValid = current.children.contains(suggested.peek());
        }

        Assert.assertTrue("Child " + suggested.peek().name + " was not found in path", isValid);

        Queue<Process> altPath = new LinkedList<>(Arrays.asList(p3,p1,p4,p2,p0));

        while(isValid && altPath.size() > 1){
            Process current = altPath.poll();

            isValid = current.children.contains(altPath.peek());
        }

        Assert.assertTrue("Child " + altPath.peek().name + " was not found in altPath", isValid);
    }
}
