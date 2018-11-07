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

public class RoomDAO extends GeneralDAO<Room> {
    private SessionFactory sessionFactory;

    public Room save(Room room) {
        return super.save(room);
    }

    public Room findById(long id) {
       return  super.findById(Room.class, id);
    }

    public void delete(long id) {
        super.delete(findById(id));
    }

    public Room update(Room room) {
        return super.update(room);
    }

    public List<Room> findRooms(Filter filter) throws BadRequestException {
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


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
