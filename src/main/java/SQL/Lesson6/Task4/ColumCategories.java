package SQL.Lesson6.Task4;

import java.util.Date;

public class ColumCategories {

    private int id;
    private String title;
    private String description;
    private Date dates;
    private String ip;

    public ColumCategories(int id, String title, String description, Date dates, String ip) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dates = dates;
        this.ip = ip;
    }
}
