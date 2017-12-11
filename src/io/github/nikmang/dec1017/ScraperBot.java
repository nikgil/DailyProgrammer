package io.github.nikmang.dec1017;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Author: fvandepitte
 * Link: https://www.reddit.com/r/dailyprogrammer/comments/41hp6u/20160118_challenge_250_easy_scraping/
 * Difficulty: Easy
 */
public class ScraperBot {

    private static Map<Integer, List<Post>> posts;

    private RedditClient client;
    private Pattern titleFormat;

    public ScraperBot() throws OAuthException {
        UserAgent agent = UserAgent.of("script", "io.github.<username>", "v0.1", "<username>");
        client = new RedditClient(agent);

        Credentials cred = Credentials.script("<username>", "<password>", "<public key>", "<private key>");
        OAuthData data = client.getOAuthHelper().easyAuth(cred);

        client.authenticate(data);

        posts = new HashMap<>();
    }

    private List<Submission> getHeadlines(int amount) {
        SubredditPaginator sbp = new SubredditPaginator(client);
        sbp.setSubreddit("dailyprogrammer");
        sbp.setLimit(amount);
        sbp.setSorting(Sorting.NEW);
        sbp.next(true);


        return sbp.getCurrentListing();
    }

    private boolean isValid(String s) {
        //may need to add multiple formats
        if(titleFormat == null) {
            String difficulty = "\\[(Easy|Intermediate|Hard)]";
            String date = "\\[(\\d{4}|\\d{2})-(\\d|\\d{2})-(\\d|\\d{2})]";
            String challenge = "#\\d+";

            titleFormat = Pattern.compile(date + " Chal(lenge|enge) " + challenge + " " + difficulty + " .*");
        }

        return titleFormat.matcher(s).matches();
    }

    public List<Post> getPosts(List<Submission> headlines) {
        List<Post> posts = new ArrayList<>();

        for(Submission s : headlines) {
            if(isValid(s.getTitle())) {
                String[] split = s.getTitle().split("\\s");
                String difficulty = split[3].substring(1,split[3].length()-1);
                int challengeNo = Integer.parseInt(split[2].substring(1));

                StringBuilder sb = new StringBuilder();

                for(int i=4; i<split.length; i++) {
                    sb.append(split[i]).append(" ");
                }

                Post p = new Post(s.getCreated(), difficulty, sb.toString().trim(), challengeNo, s.getPermalink());

                posts.add(p);

                List<Post> inMap = ScraperBot.posts.get(challengeNo);

                //This adds to simple searching
                if(inMap != null) {
                    inMap.add(p);
                } else {
                    List<Post> list = new ArrayList<>();
                    list.add(p);

                    ScraperBot.posts.put(challengeNo, list);
                }
            } else {
                System.out.println("Unable to add: " + s.getTitle());
            }
        }

        return posts;
    }

    public static void main(String[] args) {
        try {
            ScraperBot sb = new ScraperBot();

            sb.getPosts(sb.getHeadlines(100));

            SimpleMarkdown sm = new SimpleMarkdown();
            sm.saveData(posts);

            //TODO: scanner for searching
        } catch (OAuthException | IOException e) {
            e.printStackTrace();
        }
    }
}
