package JDBC.Lesson2.Task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "root";
    private static final String PASS = "260258aaa";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + JDBC_DRIVER);
            e.printStackTrace();
        }
    }

    public void saveProduct() {
        sendQuaery("INSERT INTO PRODUCT VALUES(9899,'toy', 'for children', '60')");

    }

    public void deleteProducts() {
        sendQuaery("DELETE FROM PRODUCT WHERE NAME != 'toy'");
    }

    public void deleteProductsByPrice() {
        sendQuaery("DELETE FROM PRODUCT WHERE PRICE < 100");

    }

    private void sendQuaery(String quaery) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            statement.executeUpdate(quaery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
