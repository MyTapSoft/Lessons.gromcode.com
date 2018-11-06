package JDBC.Lesson8;

import JDBC.Lesson8.DAO.*;
import JDBC.Lesson8.Model.*;

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

        Order order = new Order();
        order.setMoneyPaid(54.4);
        order.setUserOrdered(user);
        order.setDateTo(new Date());
        order.setDateFrom(new Date());
        order.setRoom(room);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        hotel.setRooms(rooms);
        user.setOrders(orders);


        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        Room room1 = roomDAO.findById(48);
        System.out.println(room1.getHotel());
    }

}
