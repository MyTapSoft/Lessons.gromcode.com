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

public class OrderDAO {
    private static SessionFactory sessionFactory;

    public static Order save(Order order) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(order);
            transaction.commit();
            System.out.println("Done");
        } catch (HibernateException e) {
            System.err.println("Can't save order with ID: " + order.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return order;
    }

    public static Order findById(long id) {
        Transaction transaction = null;
        Order result = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            result = session.get(Order.class, id);
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't findById hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
        return result;
    }
    public static Order findById(long roomId, long userId) throws BadRequestException {
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
        if (result == null) throw new BadRequestException("Order with room ID: " + roomId + " and user ID: " + userId + " doesn't exist");
        return result;
    }

    public static void delete(long id){
        Transaction transaction = null;
        try(Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(session.get(Order.class, id));
            transaction.commit();
            System.out.println("Done");

        }catch (HibernateException e) {
            System.err.println("Can't delete hotel with ID: " + id);
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    public static Order update(Order order) {
        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(order);
            transaction.commit();
            System.out.println("Done");

        } catch (HibernateException e) {
            System.err.println("Can't update order with ID: " + order.getId());
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
        }
        return order;
    }


    private static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
