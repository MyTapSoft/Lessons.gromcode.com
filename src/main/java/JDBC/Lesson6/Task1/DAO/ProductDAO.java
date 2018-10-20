package JDBC.Lesson6.Task1.DAO;

import JDBC.Lesson6.Task1.Model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    public static Product save(Product product) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with save transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return product;
    }

    public static Product delete(Product product) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(product);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with delete transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return product;
    }

    public static Product update(Product product) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(product);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with update transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return product;
    }

    public static void saveAll(List<Product> products) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            for (Product product : products) {
                session.save(product);
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with saveAll transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
    }
    public static void deleteAll(List<Product> products) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            for (Product product : products) {
                session.delete(product);
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with deleteAll transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
    }
    public static void updateAll(List<Product> products) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            for (Product product : products) {
                session.update(product);
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with updateAll transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

}
