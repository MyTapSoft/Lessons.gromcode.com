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
            System.out.println("Done");
        } catch (HibernateException e) {
            System.err.println("Can't save object with ID: " + var.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
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
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't update object with ID: " + var.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return var;
    }

    protected void delete(T var) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(session.get(var.getClass(), var.getId()));
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't delete object with ID: " + var.getId());
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
