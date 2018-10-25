package JDBC.Lesson7.DAO;

import JDBC.Lesson7.Model.Hotel;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HotelDAO {
    private static SessionFactory sessionFactory;

    public static Hotel save(Hotel hotel) {
        if (hotel == null) throw new NullPointerException("Hotel's null");
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(hotel);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't save hotel with ID: " + hotel.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return hotel;
    }

    public static Hotel findById(long id) {
        Transaction transaction = null;
        Hotel result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            result = session.get(Hotel.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't findById hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return result;
    }

    public static void delete(long id){
        Transaction transaction = null;
        try(Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(session.get(Hotel.class, id));
            transaction.commit();
        }catch (HibernateException e) {
            System.err.println("Can't delete hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    public static Hotel update(Hotel hotel) {
        if (hotel == null) throw new NullPointerException("Hotel's null");
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hotel);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't update hotel with ID: " + hotel.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return hotel;
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().buildSessionFactory();
        return sessionFactory;
    }
}
