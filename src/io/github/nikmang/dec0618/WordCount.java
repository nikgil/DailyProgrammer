package io.github.nikmang.dec0618;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Nik Gil
 *
 * MapReduce implementation from a tutorial.
 */
public class WordCount {

    //Driver
    public static void main(String[] args) {
        //1. Read text
        String[] arr =getLines("input");
        //2. Split into tokens
        arr = splitWords(arr);
        //3. Count words
        arr = consolidate(arr);
        //4. Print occurences
        for(String s : arr) {
            System.out.println(s);
        }
    }

    private static String[] getLines(String inFile) {
        Path file = Paths.get("src", "io", "github", "nikmang",
                "dec0618", inFile);

        assert Files.isReadable(file);

        try {
            List<String> lst = Files.readAllLines(file);

            return lst.toArray(new String[0]);

        } catch (IOException e) {
            System.err.println("File was not found! Cannot read input");

            return new String[0];
        }
    }

    //Mapper
    private static String[] splitWords(String[] lines) {
        List<String> mapped = new ArrayList<>();

        for(String s : lines) {
            mapped.addAll(Arrays.stream(s.split("\\W+"))
                    .map(in -> (in.toLowerCase() + ",1")).collect(Collectors.toList()));
        }

        return mapped.toArray(new String[0]);
    }

    //Reducer
    private static String[] consolidate(String[] arr) {
        Map<String, Integer> count = new HashMap<>();

        for(String s : arr) {
            String key = s.split(",")[0];
            int v = Integer.parseInt(s.split(",")[1]);

            count.put(key, count.getOrDefault(key, 0)+v);
        }

        String[] arr2 = new String[count.size()];

        int i=0;

        for(Map.Entry<String, Integer> e : count.entrySet()) {
            arr2[i] = e.getKey() + "," + e.getValue();
            i++;
        }

        return arr2;
    }
}
