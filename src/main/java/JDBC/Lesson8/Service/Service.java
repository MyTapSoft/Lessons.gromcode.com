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
    private HotelDAO hotelDAO = new HotelDAO();
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();
    private OrderDAO orderDAO = new OrderDAO();

    protected List<Hotel> findHotelByName(String name) throws BadRequestException {
        if (name == null) throw new NullPointerException("Name is null");
        return hotelDAO.findHotelByName(name);
    }

    protected List<Hotel> findHotelByCity(String city) throws BadRequestException {
        if (city == null) throw new NullPointerException("City is null");
        return hotelDAO.findHotelByCity(city);
    }

    protected List<Room> findRooms(Filter filter) throws BadRequestException {
        if (filter == null) throw new NullPointerException("Filter is null");
        return roomDAO.findRooms(filter);
    }

    protected Order bookRoom(long roomId, long userId, long hotelId, Date dateFrom, Date dateTo) throws BadRequestException {
        if (dateFrom == null || dateTo == null) throw new NullPointerException("Date is null");
        if (dateTo.getTime() - dateFrom.getTime() < 0) throw new BadRequestException("Wrong dates");
        Order order = new Order();
        Room room = roomDAO.findById(roomId);
        if (room == null) throw new BadRequestException("There's no room with id: " + roomId);
        User user = userDAO.findById(userId);
        if (user == null) throw new BadRequestException("There's no user with id: " + userId);
        Hotel hotel = hotelDAO.findById(hotelId);
        if (hotel == null) throw new BadRequestException("There's no hotel with id: " + hotelId);
        validateHotel(hotel, roomId);

        double price = (dateTo.getTime() - dateFrom.getTime()) / (1000 * 60 * 60) * room.getPrice();
        order.setRoom(room);
        order.setDateFrom(dateFrom);
        order.setDateTo(dateTo);
        order.setUserOrdered(user);
        order.setMoneyPaid(price);
        return orderDAO.save(order);

    }

    protected void cancelReservation(long roomId, long userId) throws BadRequestException {
        Order order = orderDAO.findById(roomId, userId);
        orderDAO.delete(order.getId());
    }

    protected User registerUser(User user) throws BadRequestException {
        if (user == null) throw new BadRequestException("User is null");
        return userDAO.save(user);
    }

    protected User login(String userName, String password) throws BadRequestException {
        if (userName == null || password == null) throw new NullPointerException("Name or password is null");
        return userDAO.login(userName, password);
    }

    protected User logout(User user) throws BadRequestException {
        if (user == null) throw new BadRequestException("User is null");
        if (!user.isLoginStatus()) throw new BadRequestException("User already logged out");
        return userDAO.logout(user);
    }

    private void validateHotel(Hotel hotel, long roomId) throws BadRequestException {
        for (Room r : hotel.getRooms()) {
            if (r.getId() == roomId) return;
        }
        throw new BadRequestException("Room: " + roomId + " doesn't belong to hotel: " + hotel.getId());
    }

}
