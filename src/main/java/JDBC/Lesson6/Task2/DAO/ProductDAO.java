package JDBC.Lesson6.Task2.DAO;

import JDBC.Lesson6.Task2.Exceptions.BadRequestException;
import JDBC.Lesson6.Task2.Model.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    public static List findById(long id) throws BadRequestException {
        Transaction transaction = null;
        List products = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("FROM PRODUCT WHERE ID = id");
            query.setParameter("id", id);
            products = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with save transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (products == null) throw new BadRequestException("File with ID: " + id + " doesn't exist");
        return products;
    }

    public static Product findByName(Product product) {
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
            System.err.println("Error with saveAll transaction");
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
            System.err.println("Error with saveAll transaction");
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
