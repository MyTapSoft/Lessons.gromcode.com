package JDBC.Lesson7.DAO;

import JDBC.Lesson7.Model.Room;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class RoomDAO {
    private static SessionFactory sessionFactory;

    public static Room save(Room room) {
        if (room == null) throw new NullPointerException("Hotel's null");
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(room);
            transaction.commit();
            System.out.println("Done");
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
            System.out.println("Done");

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
            session.delete(session.get(Room.class, id));
            transaction.commit();
            System.out.println("Done");

        }catch (HibernateException e) {
            System.err.println("Can't delete hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    public static Room update(Room room) {
        if (room == null) throw new NullPointerException("Hotel's null");
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(room);
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't update hotel with ID: " + room.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return room;
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
