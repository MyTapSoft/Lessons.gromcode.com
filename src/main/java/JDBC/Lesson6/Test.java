package JDBC.Lesson6;
import JDBC.Lesson6.Task3.DAO.ProductDAO;
import JDBC.Lesson6.Task3.Exceptions.BadRequestException;

public class Test {
    public static void main(String[] args) throws BadRequestException {

        System.out.println(ProductDAO.findById(0));

    }
}
