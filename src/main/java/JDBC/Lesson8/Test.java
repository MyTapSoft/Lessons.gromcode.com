package JDBC.Lesson8;

import JDBC.Lesson8.DAO.*;
import JDBC.Lesson8.Model.*;
import org.hibernate.collection.internal.PersistentList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        User user = new User();
//        user.setLoginStatus(false);
//        user.setCountry("USA");
//        user.setPassword("kdsfs2e");
//        user.setUserType(UserType.User);
//        user.setUserName("John");

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

        Room room2 = new Room();
        room.setBreakfastIncluded(false);
        room.setDateAvailableFrom(new Date());
        room.setNumberOfGuests(32);
        room.setPrice(102);
        room.setHotel(hotel);

        Room room3 = new Room();
        room.setBreakfastIncluded(true);
        room.setDateAvailableFrom(new Date());
        room.setNumberOfGuests(3);
        room.setPrice(10);
        room.setHotel(hotel);

//        Order order = new Order();
//        order.setMoneyPaid(54.4);
//        order.setUserOrdered(user);
//        order.setDateTo(new Date());
//        order.setDateFrom(new Date());
//        order.setRoom(room);

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(room2);
        rooms.add(room3);
        for (Room var : rooms) {
            System.out.println(var);

        }
        // List<Order> orders = new ArrayList<>();
        //orders.add(order);

        hotel.setRooms(rooms);
        // user.setOrders(orders);


//        HotelDAO hotelDAO = new HotelDAO();
//        RoomDAO roomDAO = new RoomDAO();
//        //UserDAO userDAO = new UserDAO();
//
//        System.out.println(hotelDAO.save(hotel).getId());
        //roomDAO.save(room);
    }

}
