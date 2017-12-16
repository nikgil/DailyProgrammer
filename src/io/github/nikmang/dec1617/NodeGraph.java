package io.github.nikmang.dec1617;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: jnazario
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/4ijtrt/20160509_challenge_266_easy_basic_graph/?st=jb9rngon&sh=2c28c047
 * Difficulty: Easy
 *
 * Test cases [Output]:
 * In respective files
 */
public class NodeGraph {

    public static Map<Integer, Integer> populate(int nums, List<String> edges) {
        Map<Integer, Integer> nodes = new HashMap<>();

        for(int i=1; i<=nums; i++) {
            nodes.put(i,0);
        }

        for(String s : edges) {
            String[] points = s.split("\\s");
            int[] pointsI = {Integer.valueOf(points[0]), Integer.valueOf(points[1])};

            nodes.put(pointsI[0], nodes.get(pointsI[0])+1);
            nodes.put(pointsI[1], nodes.get(pointsI[1])+1);
        }

        return nodes;
    }

    @Test
    public void test() {
        try {
            Map<Integer, Integer> populated = populate(16, Files.readAllLines(Paths.get(NodeGraph.class.getResource("input.txt").toURI())));
            List<String> out = Files.readAllLines(Paths.get(NodeGraph.class.getResource("output.txt").toURI()));
            List<String> in = new ArrayList<>();

            for(Map.Entry<Integer, Integer> entry : populated.entrySet()) {
                in.add(String.format("Node %d has a degree of %d", entry.getKey(), entry.getValue()));
            }

            Assert.assertTrue(out.containsAll(in));

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
