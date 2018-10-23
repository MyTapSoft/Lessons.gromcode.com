package JDBC.Lesson6.Task2.DAO;

import JDBC.Lesson6.Task2.Exceptions.BadRequestException;
import JDBC.Lesson6.Task2.Model.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;

    public static Product findById(long id) throws BadRequestException {
        Transaction transaction = null;
        Product result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query query = session.createQuery("FROM Product WHERE ID = :id", Product.class);
            query.setParameter("id", id);
            result = (Product) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with findById transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result == null) throw new BadRequestException("File with ID: " + id + " doesn't exist");
        return result;
    }

    public static List<Product> findByName(String name) throws BadRequestException {
        if (name == null) throw new NullPointerException("Name is NULL");
        Transaction transaction = null;
        List<Product> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Product> query = session.createQuery("FROM Product WHERE NAME = :name", Product.class);
            query.setParameter("name", name);
            result = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with findByName transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("File with name: " + name + " doesn't exist");
        return result;
    }

    public static List<Product> findByContainedName(String name) throws BadRequestException {
        if (name == null) throw new NullPointerException("Name is NULL");
        Transaction transaction = null;
        List<Product> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Product> query = session.createQuery("FROM Product WHERE NAME LIKE :name", Product.class);
            query.setParameter("name", "%" + name + "%");
            result = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with findByContainedName transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("Files that contain name: " + name + " doesn't exist");
        return result;
    }

    public static List<Product> findByPrice(int price, int delta) throws BadRequestException {
        if (price - delta < 0) throw new BadRequestException("Price's less than 0");
        Transaction transaction = null;
        List<Product> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Product> query = session.createQuery("FROM Product WHERE price >= :lowestPrice AND price <= :highestPrice", Product.class);
            query.setParameter("lowestPrice", price - delta);
            query.setParameter("highestPrice", price + delta);
            result = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Error with findByPrice transaction");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        if (result.size() == 0)
            throw new BadRequestException("Where's no Products with price diapason from : " + (price - delta) + " to" + (price + delta));
        return result;
    }

    public static List<Product> findByPriceSortedDesc(int price, int delta) throws BadRequestException {//В условии написано по убыванию, но метод назван desc?
        if (price - delta < 0) throw new BadRequestException("Price's less than 0");
        Transaction transaction = null;
        List<Product> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Product> query = session.createQuery("FROM Product WHERE price >= :lowestPrice AND price <= :highestPrice ORDER BY :order desc", Product.class);
            query.setParameter("lowestPrice", price - delta);
            query.setParameter("highestPrice", price + delta);
            query.setParameter("order", price);
            result = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Error with findByPrice transaction");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        if (result.size() == 0)
            throw new BadRequestException("Where's no Products with price diapason from : " + (price - delta) + " to" + (price + delta));
        return result;
    }

    public static List<Product> findByNameSortedAsc(String name) throws BadRequestException {//Поиск продуктов по имени + сортировка по имени? Это как? У их же имена одинаковые.
        Transaction transaction = null;
        List<Product> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Product> query = session.createQuery("FROM Product WHERE name = :name ORDER BY :order asc", Product.class);
            query.setParameter("name", name);
            query.setParameter("order", name);
            result = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Error with findByNameSortedAsc transaction");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("File with name: " + name + " doesn't exist");
        return result;

    }

    public static List<Product> findByNameSortedDesc(String name) throws BadRequestException {//Поиск продуктов по имени + сортировка по имени? Это как? У их же имена одинаковые.
        Transaction transaction = null;
        List<Product> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Product> query = session.createQuery("FROM Product WHERE name = :name ORDER BY :order desc", Product.class);
            query.setParameter("name", name);
            query.setParameter("order", name);
            result = query.list();
            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Error with findByNameSortedDesc transaction");
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("File with name: " + name + " doesn't exist");
        return result;

    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

}
