package JDBC.Lesson8.DAO;


import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.Filter;
import JDBC.Lesson8.Model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

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
            System.err.println("Can't update room with ID: " + room.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return room;
    }

    public static List<Room> findRooms(Filter filter) throws BadRequestException {
        if (filter == null) throw new NullPointerException("Filter is NULL");
        Transaction transaction = null;
        List<Room> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Room> query = session.createQuery("FROM Room WHERE NUMBER_OF_GUESTS = :numberOfGuests AND PRICE = :price AND BREAKFAST_INCLUDED = :breakfastIncluded AND DATE_AVAILABLE_FROM = :dateAvailableFrom AND HOTEL_ID = :hotelId", Room.class);
            query.setParameter("numberOfGuests", filter.getNumberOfGuests());
            query.setParameter("price", filter.getPrice());
            query.setParameter("breakfastIncluded", filter.isBreakfastIncluded());
            query.setParameter("dateAvailableFrom", filter.getDateAvailableFrom());
            query.setParameter("hotelId", filter.getGetHotelId());
            result = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with filter transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("Hotel with filter: " + filter + " doesn't exist");
        return result;
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
