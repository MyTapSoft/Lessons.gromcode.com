package JDBC.Lesson8.Service;

import JDBC.Lesson8.DAO.HotelDAO;
import JDBC.Lesson8.DAO.OrderDAO;
import JDBC.Lesson8.DAO.RoomDAO;
import JDBC.Lesson8.DAO.UserDAO;
import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.*;


import java.util.Date;
import java.util.List;

public class Service {
    public List<Hotel> findHotelByName(String name) throws BadRequestException {
        if (name == null) throw new NullPointerException("Name is null");
        return HotelDAO.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) throws BadRequestException {
        if (city == null) throw new NullPointerException("City is null");
        return HotelDAO.findHotelByCity(city);
    }

    public List<Room> findRooms(Filter filter) throws BadRequestException {
        if (filter == null) throw new NullPointerException("Filter is null");
        return RoomDAO.findRooms(filter);
    }

    public Order bookRoom(long roomId, long userId, long hotelId, Date dateFrom, Date dateTo) throws BadRequestException {
        if (dateFrom == null || dateTo == null) throw new NullPointerException("Date is null");
        if (dateTo.getTime() - dateFrom.getTime() < 0) throw new BadRequestException("Wrong dates");
        Order order = new Order();
        Room room = RoomDAO.findById(roomId);
        if (room == null) throw new BadRequestException("There's no room with id: " + roomId);
        User user = UserDAO.findById(userId);
        if (user == null) throw new BadRequestException("There's no user with id: " + userId);
        Hotel hotel = HotelDAO.findById(hotelId);
        if (hotel == null) throw new BadRequestException("There's no hotel with id: " + hotelId);
        if (!hotel.getRooms().contains(room)) throw new BadRequestException("Room: " + roomId + " doesn't belong to hotel: " + hotelId);

        double price = (dateTo.getTime() - dateFrom.getTime()) / (1000 * 60 * 60) * room.getPrice();
        order.setRoom(room);
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setUserOrdered(user);
        order.setMoneyPaid(price);
        return OrderDAO.save(order);

    }

    public void cancelReservation(long roomId, long userId) throws BadRequestException {
        Order order = OrderDAO.findById(roomId, userId);
        OrderDAO.delete(order.getId());
    }

    public User registerUser(User user) throws BadRequestException {
        if (user == null) throw new BadRequestException("User is null");
        return UserDAO.save(user);
    }

    public User login(String userName, String password) throws BadRequestException {
        if (userName == null || password == null) throw new NullPointerException("Name or password is null");
        return UserDAO.login(userName, password);
    }

    public User logout(User user) throws BadRequestException {
        if (user == null) throw new BadRequestException("User is null");
        if (!user.isLoginStatus()) throw new BadRequestException("User already logged out");
        return UserDAO.logout(user);
    }

}
