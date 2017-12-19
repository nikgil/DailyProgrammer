package io.github.nikmang.dec1817;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Author: G33kDude
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/3yiy2d/20151228_challenge_247_easy_secret_santa/?st=jbcvuxuo&sh=75ed4a91
 * Difficulty: Easy
 * Input [Output]:
 * on sheets [Output is random]
 */
public class SecretSanta {

    private List<String> people;
    private Map<String, List<String>> families;

    public SecretSanta(String fileName) throws URISyntaxException, IOException {
        List<String> temp = Files.readAllLines(Paths.get(SecretSanta.class.getResource(fileName).toURI()));
        people = new ArrayList<>();
        families = new HashMap<>();

        for (String s : temp) {
            String[] names = s.split("\\s");

            List<String> family = Arrays.asList(names);
            people.addAll(family);

            if (names.length > 1) {
                new ArrayList<>();
                for (String name : names) {
                    families.put(name, family);
                }
            }
        }

        assert people.size() % 2 == 0;
    }

    public Map<String, String> getPairs() {
        Random r = new Random();
        Map<String, String> pairs = new HashMap<>();
        List<String> people = new ArrayList<>(this.people);

        String child = people.get(r.nextInt(people.size()));

        while (!people.isEmpty()) {
            String parent = people.remove(r.nextInt(people.size()));
            String childRecepient = pairs.get(child);

            //Keep redoing this if person gets themselves (only the case for the first one)
            //And check if people end up sending to each other (avoid closed loops)
            while (child.equals(parent) || (childRecepient != null && childRecepient.equals(parent)) || (families.containsKey(parent) && families.get(parent).contains(child))) {
                String parent2 = people.remove(r.nextInt(people.size()));
                people.add(parent);
                parent = parent2;
            }

            pairs.put(parent, child);
            child = parent;
        }

        return pairs;
    }

    List<String> getPeople() {
        return Collections.unmodifiableList(people);
    }
}
