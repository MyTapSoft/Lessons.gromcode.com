package JDBC.Lesson7.DAO;

import JDBC.Lesson7.Model.Room;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class RoomDAO {
    private static SessionFactory sessionFactory;

    public static Room save(Room room) {
        if (room == null) throw new NullPointerException("Room's null");
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(room);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't save room with ID: " + room.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return room;
    }

    public static Room findById(long id) {
        Transaction transaction = null;
        Room result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            result = session.get(Room.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't findById Room with ID: " + id);
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
            session.delete(session.get(Room.class, id));
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't delete Room with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    public static Room update(Room hotel) {
        if (hotel == null) throw new NullPointerException("Hotel's null");
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hotel);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Can't update Room with ID: " + hotel.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return hotel;
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
