package JDBC.Lesson8.DAO;

import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.Hotel;
import JDBC.Lesson8.Model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends GeneralDAO<Order> {
    private SessionFactory sessionFactory;

    public Order save(Order order) {
        return super.save(order);
    }

    public Order findById(long id) {
        return super.findById(new Order(), id);
    }

    public Order findById(long roomId, long userId) throws BadRequestException {
        Transaction transaction = null;
        Order result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            org.hibernate.query.Query<Order> query = session.createQuery("FROM Order WHERE ROOM_ID = :roomId AND USER_ID = :userId", Order.class);
            query.setParameter("roomId", roomId);
            query.setParameter("userId", userId);
            result = query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println("Error with findById transaction");
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        if (result == null)
            throw new BadRequestException("Order with room ID: " + roomId + " and user ID: " + userId + " doesn't exist");
        return result;
    }

    public void delete(long id) {
        super.delete(findById(id));
    }

    public Order update(Order order) {
        return super.update(order);
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
