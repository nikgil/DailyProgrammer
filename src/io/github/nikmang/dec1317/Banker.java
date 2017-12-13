package io.github.nikmang.dec1317;

import io.github.nikmang.dec1017.Post;

import java.util.*;

/**
 * Author:  polypeptide147
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/7jkfu5/20171213_challenge_344_intermediate_bankers/
 * Difficulty: Medium
 *
 * Sample {Output}:
 * [3 3 2]
 * [0 1 0 7 5 3]
 * [2 0 0 3 2 2]
 * [3 0 2 9 0 2]
 * [2 1 1 2 2 2]
 * [0 0 2 4 3 3]
 *
 * {P1, P4, P3, P0, P2}
 */
public class Banker {

    private int[] available;
    List<Process> processes;

    public Banker(int[] available) {
        this.available = available;
        this.processes = new ArrayList<>();
    }

    public List<Process> runProgram() {
        List<Process> orders = new ArrayList<>();

        for(Process p : processes) {
            if(isAvailable(Arrays.copyOf(available, available.length), p, new HashSet<>(processes))) {
                orders.add(p);
            }
        }

        return orders;
    }

    private boolean isAvailable(int[] open, Process p, Set<Process> next) {
        if(!isAble(open, p)) {
            return false;
        }

        next.remove(p);

        if(next.isEmpty()) {
            return true;
        }

        //Add free space
        for(int i=0; i<open.length; i++) {
            open[i] += p.allocation[i];
        }

        for(Process p2 : next) {
            if(isAvailable(Arrays.copyOf(open, open.length), p2, new HashSet<>(next))) {
                p.children.add(p2);
            }
        }

        return !p.children.isEmpty();
    }

    private boolean isAble(int[] available, Process processes) {
        return available[0] >= (processes.needed[0]-processes.allocation[0])
                && available[1] >= (processes.needed[1]-processes.allocation[1])
                && available[2] >= (processes.needed[2]-processes.allocation[2]);
    }
}
