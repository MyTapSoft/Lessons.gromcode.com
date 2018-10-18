package JDBC.Lesson4.Task1;

import JDBC.Lesson3.Task1.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDemo {

    public void save(List<Product> products) {
        if (products.size() == 0){
            System.out.println("List is empty");
            return;
        }
        try (Connection connection = getConnection()) {
            saveList(products, connection);
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    private void saveList(List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES(?, ?, ?, ?) ")) {
            connection.setAutoCommit(false);
            for (Product product : products) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int res = preparedStatement.executeUpdate();
                if (res == 0) {
                    throw new SQLException("Product hasn't been stored. Product ID: " + product.getId());
                }
            }
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

    }

    private Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
        String USER = "root";
        String PASS = "260258aaa";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
