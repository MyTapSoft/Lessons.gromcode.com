package JDBC.Lesson8.DAO;

import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO {
    private static SessionFactory sessionFactory;

    public static List<Hotel> findHotelByName(String name) throws BadRequestException {
        Transaction transaction = null;
        List<Hotel> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Hotel> query = session.createQuery("FROM Hotel WHERE NAME = :name", Hotel.class);
            query.setParameter("name", name);
            result = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with findByName transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("Hotel with name: " + name + " doesn't exist");
        return result;
    }
    public static List<Hotel> findHotelByCity(String city) throws BadRequestException {
        Transaction transaction = null;
        List<Hotel> result = new ArrayList<>();
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Hotel> query = session.createQuery("FROM Hotel WHERE CITY = :city", Hotel.class);
            query.setParameter("city", city);
            result = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with findByName transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result.size() == 0) throw new BadRequestException("Hotel with city: " + city + " doesn't exist");
        return result;
    }

    public static Hotel save(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(hotel);
            transaction.commit();
            System.out.println("Done");
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
            session.delete(session.get(Hotel.class, id));
            transaction.commit();
            System.out.println("Done");

        }catch (HibernateException e) {
            System.err.println("Can't delete hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    public static Hotel update(Hotel hotel) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hotel);
            transaction.commit();
            System.out.println("Done");

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
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
