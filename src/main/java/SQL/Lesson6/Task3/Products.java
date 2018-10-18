package SQL.Lesson6.Task3;

public class Products {
    private int productID;
    private String productName;
    private Suppliers supplierID;
    private Categories categoryID;
    private int quantityPerUnit;
    private int unitPrice;
    private int unitsInStock;
    private int unitsOnOrder;
    private int recorderLevel;
    private int discontinued;


    public Products(int productID, String productName, Suppliers supplierID, Categories categoryID, int quantityPerUnit, int unitPrice, int unitsInStock, int unitsOnOrder, int recorderLevel, int discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.categoryID = categoryID;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.recorderLevel = recorderLevel;
        this.discontinued = discontinued;
    }
}
