package SQL.Lesson6.Task4;

import java.util.Date;

public class ColumSubcategories {
    private int id;
    private ColumCategories idCategory;
    private String title;
    private String description;
    private Date dates;
    private String ip;

    public ColumSubcategories(int id, ColumCategories idCategory, String title, String description, Date dates, String ip) {
        this.id = id;
        this.idCategory = idCategory;
        this.title = title;
        this.description = description;
        this.dates = dates;
        this.ip = ip;
    }
}
