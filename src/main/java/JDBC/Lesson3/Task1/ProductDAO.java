package JDBC.Lesson3.Task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getProducts() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT");
            if (resultSet == null) throw new SQLException("Product is empty");
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product save(Product product) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCT VALUES(?, ?, ?, ?)")) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getPrice());

            int res = preparedStatement.executeUpdate();
            if (res == 0) throw new SQLException("Nothing has saved");
            System.out.println(res + " products has saved successful");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product getUpdate(Product product) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET  name = ?, description = ?, price = ? WHERE id = ?")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());

            int res = preparedStatement.executeUpdate();
            if (res == 0) throw new SQLException("Nothing has updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void delete(long id) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE PRODUCT WHERE id = ?")) {
            preparedStatement.setLong(1, id);

            int res = preparedStatement.executeUpdate();
            if (res == 0) throw new SQLException("Nothing has deleted");
            System.out.println("Product has deleted successful. ID + " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
        String USER = "root";
        String PASS = "260258aaa";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
