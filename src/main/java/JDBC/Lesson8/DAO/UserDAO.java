package JDBC.Lesson8.DAO;

import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class UserDAO {
    private static SessionFactory sessionFactory;

    public static User save(User user) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(user);
            transaction.commit();
            System.out.println("Done");
        } catch (HibernateException e) {
            System.err.println("Can't save User with ID: " + user.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return user;
    }

    public static User findById(long id) {
        Transaction transaction = null;
        User result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            result = session.get(User.class, id);
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't findById User with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return result;
    }

    public static void delete(long id) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't delete User with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    public static User update(User user) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(user);
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't update User with ID: " + user.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return user;
    }

    public static User login(String name, String password) throws BadRequestException {
        Transaction transaction = null;
        User result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<User> query = session.createQuery("FROM User WHERE NAME = :name AND PASSWORD = :password", User.class);
            query.setParameter("name", name);
            query.setParameter("password", password);
            result = query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with login transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result == null)
            throw new BadRequestException("User with name: " + name + " and password: " + password + " doesn't exist");
        if (result.isLoginStatus()) throw new BadRequestException("User already logged in");
        result.setLoginStatus(true);
        return update(result);
    }

    public static User logout(User user) {
        user.setLoginStatus(false);
        return update(user);
    }

    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
