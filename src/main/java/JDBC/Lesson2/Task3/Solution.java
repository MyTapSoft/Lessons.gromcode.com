package JDBC.Lesson2.Task3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "root";
    private static final String PASS = "260258aaa";


    public List<Product> getAllProducts() throws SQLException {
        return sendQuarry("SELECT * FROM PRODUCT");

    }

    public List<Product> getProductsByPrice() throws SQLException {
        return sendQuarry("SELECT * FROM PRODUCT WHERE PRICE <= 1");
    }

    public List<Product> getProductsByDescription() throws SQLException {
        return sendQuarry("SELECT * FROM PRODUCT WHERE (DBMS_LOB.GETLENGTH(DESCRIPTION) > 50");
    }

    private List<Product> sendQuarry(String quarry) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            List<Product> products = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(quarry);
            if (resultSet == null) throw new Exception("Nothing has change");
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new SQLException("Error");
    }
}
