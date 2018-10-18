package SQL.Lesson6.Task3;

import java.util.Date;

public class Employees {
    private int employeeId;
    private String lasdName;
    private String firstName;
    private String Title;
    private String TitleOfCourtesy;
    private Date birthDate;
    private Date hireDate;
    private String address;
    private String city;
    private String region;
    private int postalCode;
    private String country;
    private int homePhone;
    private int extansion;
    private String photo;
    private String notes;
    private int reportsTo;

    public Employees(int employeeId, String lasdName, String firstName, String title, String titleOfCourtesy, Date birthDate, Date hireDate, String address, String city, String region, int postalCode, String country, int homePhone, int extansion, String photo, String notes, int reportsTo) {
        this.employeeId = employeeId;
        this.lasdName = lasdName;
        this.firstName = firstName;
        Title = title;
        TitleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.homePhone = homePhone;
        this.extansion = extansion;
        this.photo = photo;
        this.notes = notes;
        this.reportsTo = reportsTo;
    }
}
