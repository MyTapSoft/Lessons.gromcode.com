package JDBC.Lesson8.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GeneralDAO<T extends IdEntity> {
    private SessionFactory sessionFactory;

    protected T save(T var) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(var);
            transaction.commit();
            System.out.println("Save method ends well");
        } catch (HibernateException e) {
            System.err.println("Can't save object with ID: " + var.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            closeSession();
        }
        return var;
    }

    protected T update(T var) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(var);
            transaction.commit();
            System.out.println("Update method ends well");

        } catch (HibernateException e) {
            System.err.println("Can't update object with ID: " + var.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            closeSession();
        }
        return var;
    }

    protected void delete(Class<?> objectClass, long id) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            T object = findById(objectClass, id);
            session.delete(object);
            transaction.commit();
            System.out.println("Delete method ends well");

        } catch (HibernateException e) {
            System.err.println("Can't delete object with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
            closeSession();
        }
    }

    protected T findById(Class<?> objectClass, long id) {
        Transaction transaction = null;
        T result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            result = (T) session.get(objectClass, id);
            transaction.commit();
            System.out.println("Get method ends well");

        } catch (HibernateException e) {
            System.err.println("Can't findById hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
            closeSession();
        }
        return result;
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
