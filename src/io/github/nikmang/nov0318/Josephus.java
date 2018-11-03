package io.github.nikmang.nov0318;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Can be done more efficiently (mine is (O(n))
 *
 * @author Nik Gil
 * Date: 3/11/2018
 */
public class Josephus {

    public static void main(String[] args) {
        System.out.println(josephus(14, 2));
    }

    /**
     *
     * @param fighters Amount of fighters to die.
     * @param nth The nth person to die each time.
     *
     * @return Which numbered person will survive to be last.
     */
    private static int josephus(int fighters, int nth) {
        Queue<Integer> n = new LinkedList<>();

        for(int i=0; i<fighters; i++) {
            n.offer(i+1);
        }

        int i = 1;
        while(n.size() > 1) {
            if(i%nth == 0) {
                n.poll();
            } else {
                n.offer(n.poll());
            }

            i++;
        }

        return !n.isEmpty() ? n.poll(): -1;
    }
}
