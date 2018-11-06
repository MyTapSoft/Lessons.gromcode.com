package JDBC.Lesson8.DAO;

import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.Hotel;
import JDBC.Lesson8.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {
    private SessionFactory sessionFactory;

    public List<Hotel> findHotelByName(String name) throws BadRequestException {
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

    public List<Hotel> findHotelByCity(String city) throws BadRequestException {
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

    public Hotel save(Hotel hotel) {
        return super.save(hotel);
    }

    public Hotel findById(long id) {
        return super.findById(new Hotel(), id);
    }

    public void delete(long id) {
        super.delete(findById(id));
    }

    public Hotel update(Hotel hotel) {
        return super.update(hotel);
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
