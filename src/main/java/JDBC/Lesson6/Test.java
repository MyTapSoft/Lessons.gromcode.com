package JDBC.Lesson6;

import JDBC.Lesson6.Task1.DAO.ProductDAO;
import JDBC.Lesson6.Task1.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setDescription(String.valueOf(i));
            product.setName(String.valueOf(i));
            product.setPrice(i);
            products.add(product);
        }

        ProductDAO.saveAll(products);
    }
}
