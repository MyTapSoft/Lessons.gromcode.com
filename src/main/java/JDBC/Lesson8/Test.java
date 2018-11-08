package JDBC.Lesson8;

import JDBC.Lesson8.DAO.*;
import JDBC.Lesson8.Model.*;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        User user = new User();
        user.setLoginStatus(false);
        user.setCountry("USA");
        user.setPassword("kdsfs2e");
        user.setUserType(UserType.User);
        user.setUserName("John");

        Hotel hotel = new Hotel();
        hotel.setCity("Las Vegas");
        hotel.setCountry("USA");
        hotel.setName("Le Vadax");
        hotel.setStreet("Washington sqr.");

        Room room = new Room();
        room.setBreakfastIncluded(true);
        room.setDateAvailableFrom(new Date());
        room.setNumberOfGuests(3);
        room.setPrice(10);
        room.setHotel(hotel);
        System.out.println(room);

        Room room2 = new Room();
        room.setBreakfastIncluded(false);
        room.setDateAvailableFrom(new Date());
        room.setNumberOfGuests(32);
        room.setPrice(102);
        room.setHotel(hotel);
        System.out.println(room2);

        Room room3 = new Room();
        room.setBreakfastIncluded(true);
        room.setDateAvailableFrom(new Date());
        room.setNumberOfGuests(3);
        room.setPrice(10);
        room.setHotel(hotel);
        System.out.println(room3);

        Order order = new Order();
        order.setMoneyPaid(54.4);
        order.setUserOrdered(user);
        order.setDateTo(new Date());
        order.setDateFrom(new Date());
        order.setRoom(room);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);

        hotel.setRooms(rooms);
        user.setOrders(orders);


        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        //UserDAO userDAO = new UserDAO();

        Hotel hottel = hotelDAO.save(hotel);
        for (Room r: hotelDAO.findById(hottel.getId()).getRooms()
             ) {
            System.out.println(r);
        }

    }

}
