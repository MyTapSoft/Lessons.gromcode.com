package JDBC.Lesson8.DAO;

import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class UserDAO extends GeneralDAO<User> {
    private SessionFactory sessionFactory;

    public User save(User user) {
        return super.save(user);
    }

    public User findById(long id) {
        return super.findById(User.class, id);
    }

    public void delete(long id) {
        super.delete(User.class, id);
    }

    public User update(User user) {
        return super.update(user);
    }

    public User login(String name, String password) throws BadRequestException {
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
        if (result == null) {
            closeSession();
            throw new BadRequestException("User with name: " + name + " and password: " + password + " doesn't exist");
        }
        if (result.isLoginStatus()) {
            closeSession();
            throw new BadRequestException("User already logged in");
        }
        result.setLoginStatus(true);
        return update(result);
    }

    public User logout(User user) {
        user.setLoginStatus(false);
        return update(user);
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    private void closeSession() {
        sessionFactory.close();
    }
}
