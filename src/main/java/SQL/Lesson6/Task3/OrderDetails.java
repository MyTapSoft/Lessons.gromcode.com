package SQL.Lesson6.Task3;

public class OrderDetails {

    private Orders orderID;
    private Products productID;
    private int unitPrice;
    private int quantity;
    private int discount;

    public OrderDetails(Orders orderID, Products productID, int unitPrice, int quantity, int discount) {
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }
}
