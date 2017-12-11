package io.github.nikmang.dec0817;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/6j7k3x/20170624_challenge_320_hard_path_to_philosophy/?st=jax8a68x&sh=8bd1a7a3
 * Difficulty: Hard
 */
public class WebParser {

    public static void main(String[] args) {
        WebParser parser = new WebParser("https://en.wikipedia.org/wiki/Ireland");

        try {
            Link last = parser.parse();

            parser.printPath(parser.getPath(last));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String startUrl;
    private Set<String> visited;
    private Queue<String> toVisit;

    public WebParser(String startUrl) {
        this.startUrl = startUrl;
        visited = new HashSet<>();
        toVisit = new LinkedList<>();
    }

    public Link parse() throws IOException {
        Link parent = null;

        do {
            visited.add(startUrl);

            Connection con = Jsoup.connect(startUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
            Document doc = con.get();

            Element head = doc.getElementById("firstHeading");
            Link current = new Link(startUrl, head.text());

            current.parent = parent;
            parent = current;

            Elements links = doc.getElementById("bodyContent").select("a[href]");

            for(Element link : links) {
                String url = link.absUrl("href");

                if(url.startsWith("https://en.wikipedia.org/wiki/") && url.lastIndexOf(":") == 5 && !url.contains("#")) {
                    if(url.equals("https://en.wikipedia.org/wiki/Philosophy")) {
                        Link phil = new Link("https://en.wikipedia.org/wiki/Philosophy", "Philosophy");
                        phil.parent = parent;

                        return phil;
                    }

                    if(!visited.contains(url))
                        toVisit.add(url);
                }
            }

            startUrl = toVisit.poll();
        } while(!toVisit.isEmpty());

        return null;
    }

    public Stack<Link> getPath(Link target) {
        Stack<Link> stack = new Stack<>();

        while(target != null) {
            stack.add(target);
            target = target.parent;
        }


        return stack;
    }

    public void printPath(Stack<Link> path) {
        while(!path.isEmpty()) {
            System.out.println(path.pop().name);
        }
    }

    private class Link {
        private Link parent;
        private String url, name;

        Link(String url, String name) {
            this.parent = null;
            this.url = url;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Link))
                return false;

            Link l = (Link) o;

            return l.url.equals(this.url);
        }

        @Override
        public int hashCode() {
            return url.hashCode();
        }
    }
}
