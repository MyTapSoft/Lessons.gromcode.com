package JDBC.Lesson6;

import JDBC.Lesson6.Task2.DAO.ProductDAO;
import JDBC.Lesson6.Task2.Exceptions.BadRequestException;

import java.util.List;


public class Test {
    public static void main(String[] args) throws BadRequestException {
//        List list = ProductDAO.findByContainedName("efd");
//        for (Object p: list) {
//            System.out.println(p);
//        }
        System.out.println(ProductDAO.findByPriceSortedDesc(10,5));

    }
}
