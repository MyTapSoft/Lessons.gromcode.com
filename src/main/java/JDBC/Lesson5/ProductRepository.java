package JDBC.Lesson5;

import JDBC.Lesson5.Task1.Model.Product;
import JDBC.Lesson5.Task1.Utils.HibernateUtils;
import org.hibernate.Session;

public class ProductRepository {
    private static Session session = new HibernateUtils().createSessionFactory().openSession();

    public static void save(Product product) {
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        System.out.println("Done");
    }

    public static void delete(long id) {
        session.getTransaction().begin();
        session.delete(session.get(Product.class, id));
        session.getTransaction().commit();
        System.out.println("Done");
    }

    public static void update(Product product) {
        session.getTransaction().begin();
        session.update(product);
        session.getTransaction().commit();
        System.out.println("Done");
    }
}
