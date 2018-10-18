package SQL.Lesson6.Task4;

import java.util.Date;

public class TorumPollsOptions {
    private int id;
    private TorumPosts idPost;
    private String title;
    private Date dates;

    public TorumPollsOptions(int id, TorumPosts idPost, String title, Date dates) {
        this.id = id;
        this.idPost = idPost;
        this.title = title;
        this.dates = dates;
    }
}
