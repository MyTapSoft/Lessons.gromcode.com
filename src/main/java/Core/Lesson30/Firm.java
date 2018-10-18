package Core.Lesson30;

import java.util.Date;
import java.util.List;

public class Firm {
    private Date dateFounded;
    private static List<Department> departments;
    private static List<Customer> customers;
    public Firm(Date dateFounded) {
        this.dateFounded = dateFounded;
    }
}
