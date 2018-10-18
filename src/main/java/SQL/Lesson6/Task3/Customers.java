package SQL.Lesson6.Task3;

public class Customers {
    private int customerId;
    private String companyName;
    private String contactNAme;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private int postalCode;
    private String country;
    private int phone;
    private int fax;

    public Customers(int customerId, String companyName, String contactNAme, String contactTitle, String address, String city, String region, int postalCode, String country, int phone, int fax) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactNAme = contactNAme;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
    }
}
