package JDBC.Lesson3.Task2;



import JDBC.Lesson3.Task1.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {


    public List<Product> findProductByPrice(int price, int delta) throws Exception {
        if (price < 0 || delta < 0 || price - delta <= 0) throw new Exception("Price or Delta are less than 0");
        int bottomPrice = price - delta, topPrice = price + delta;
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE PIICE >= ? AND PRICE <= ? ")) {
            statement.setInt(1, bottomPrice);
            statement.setInt(2, topPrice);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
        }
        if (products.size() == 0)
            throw new Exception("There is no one product in your price range: " + (price - delta) + " - " + (price + delta));
        return products;
    }

    public List<Product> findProductsByName(String word) throws Exception {
        validator(word);
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE NAME = ?")) {
            statement.setString(1, word);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }

        }
        if (products.size() == 0) throw new Exception("There is no one product with name " + word);
        return products;
    }

    public List<Product> findProductsWithEmptyDescription() throws Exception {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCT WHERE DESCRIPTION IS NULL");
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
        }

        if (products.size() == 0) throw new Exception("There is no one product with empty description ");

        return products;
    }

    private void validator(String word) throws Exception {
        if (word == null) throw new Exception("Word is null");
        if (word.length() < 3) throw new Exception("Length less than 3. Length is " + word.length());
        if (word.split(" ").length != 1) throw new Exception("Word consist more than one word. " + word);

        char[] chars = word.toCharArray();
        for (char ch : chars) {
            if (!(Character.isLetter(ch) || Character.isDigit(ch))) {
                throw new Exception("Word " + word + " has inappropriate char " + ch);
            }
        }

    }

    private Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
        String USER = "root";
        String PASS = "260258aaa";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
