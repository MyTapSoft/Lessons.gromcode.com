package JDBC.Lesson5;

import JDBC.Lesson5.Task1.Model.Product;

public class Test {
    public static void main(String[] args) {
        Product product = new Product();
        product.setDescription("ddd");
        product.setName("efdffg");
        product.setPrice(5410);

        ProductRepository.save(product);

    }
}
