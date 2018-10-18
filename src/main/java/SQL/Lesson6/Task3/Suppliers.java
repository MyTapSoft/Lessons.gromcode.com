package SQL.Lesson6.Task3;

public class Suppliers {
    private int supplierID;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String region;
    private int postalCode;
    private String country;
    private int phone;
    private int fax;
    private String homePage;


    public Suppliers(int supplierID, String companyName, String contactName, String contactTitle, String address, String region, int postalCode, String country, int phone, int fax, String homePage) {
        this.supplierID = supplierID;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
        this.homePage = homePage;
    }
}
