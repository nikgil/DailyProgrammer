package io.github.nikmang.dec1017;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nik on 12/10/2017.
 */
public class Post {
    private Date date;
    private String difficulty;
    private String name;
    private String link;
    private int challengeNo;

    public Post(Date date, String difficulty, String name, int challengeNo, String link) {
        this.date = date;
        this.difficulty = difficulty;
        this.name = name;
        this.challengeNo = challengeNo;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public Date getDate() {
        return date;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getName() {
        return name;
    }

    public int getChallengeNo() {
        return challengeNo;
    }

    @Override
    public int hashCode() {
        return 37*link.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Post))
            return false;

        Post p = (Post) o;

        return p.link.equals(this.link);
    }
}
