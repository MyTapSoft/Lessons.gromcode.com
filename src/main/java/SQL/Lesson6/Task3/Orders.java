package SQL.Lesson6.Task3;

import java.util.Date;

public class Orders {
    private int ordersId;
    private Customers customerId;
    private Employees employeeId;
    private Date orderDate;
    private Date requiredDate;
    private Snippers snippedDate;
    private String shipVia;
    private String freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private int shipPostalCode;
    private String shipCountry;

    public Orders(int ordersId, Customers customerId, Employees employeeId, Date orderDate, Date requiredDate, Snippers snippedDate, String shipVia, String freight, String shipName, String shipAddress, String shipCity, String shipRegion, int shipPostalCode, String shipCountry) {
        this.ordersId = ordersId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.snippedDate = snippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }
}
