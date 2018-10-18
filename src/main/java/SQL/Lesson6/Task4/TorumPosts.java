package SQL.Lesson6.Task4;

import java.util.Date;

public class TorumPosts {
    private int id;
    private ColumSubcategories idSubcategory;
    private Users idUser;
    private TorumPosts parentPost;
    private String title;
    private String content;
    private boolean isPoll;
    private Date dates;
    private int ip;

    public TorumPosts(int id, ColumSubcategories idSubcategory, Users idUser, TorumPosts parentPost, String title, String content, boolean isPoll, Date dates, int ip) {
        this.id = id;
        this.idSubcategory = idSubcategory;
        this.idUser = idUser;
        this.parentPost = parentPost;
        this.title = title;
        this.content = content;
        this.isPoll = isPoll;
        this.dates = dates;
        this.ip = ip;
    }
}
