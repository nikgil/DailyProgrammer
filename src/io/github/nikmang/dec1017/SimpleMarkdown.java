package io.github.nikmang.dec1017;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

/**
 * Created by Nik on 12/10/2017.
 */
public class SimpleMarkdown {

    private Path file;

    public SimpleMarkdown() throws IOException {
        file = Paths.get(".", "src", "io", "github", "nikmang", "dec1017", "Markdown.md");

        if(!Files.exists(file)) {
            String top = "| Easy | Intermediate | Hard | Weekly/Bonus|\n----|--------------|------|-------------|\n";

            Files.createFile(file);
            Files.write(file, top.getBytes(), StandardOpenOption.WRITE);
        }
    }

    public void saveData(Map<Integer, List<Post>> input) throws IOException {
        for(List<Post> entry : input.values()) {
            StringBuilder sb = new StringBuilder();
            String easy = "[]()";
            String medium = "[]()";
            String hard = "[]()";

            for(Post p : entry) {
                if(p.getDifficulty().equals("Easy")) {
                    easy = String.format("[[%s] Challenge #%d [%s] %s](%s)", p.getFormattedDate(), p.getChallengeNo(), p.getDifficulty(), p.getName(), p.getLink());
                } else if(p.getDifficulty().equals("Hard")) {
                    hard = String.format("[[%s] Challenge #%d [%s] %s](%s)", p.getFormattedDate(), p.getChallengeNo(), p.getDifficulty(), p.getName(), p.getLink());
                } else {
                    medium = String.format("[[%s] Challenge #%d [%s] %s](%s)", p.getFormattedDate(), p.getChallengeNo(), p.getDifficulty(), p.getName(), p.getLink());
                }
            }
            sb.append("| ").append(easy).append(" | ").append(medium).append(" | ").append(hard).append(" | ").append(" **-** |\n");

            Files.write(file, sb.toString().getBytes(), StandardOpenOption.APPEND);
        }
    }

    public static void main(String[] args) {
        try {
            new SimpleMarkdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
