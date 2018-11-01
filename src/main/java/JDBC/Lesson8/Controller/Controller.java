package JDBC.Lesson8.Controller;

import JDBC.Lesson8.Exceptions.BadRequestException;
import JDBC.Lesson8.Model.*;
import JDBC.Lesson8.Service.Service;

import java.util.Date;
import java.util.List;

public class Controller extends Service {
    public List<Hotel> findHotelByName(String name) throws BadRequestException {
        return super.findHotelByName(name);

    }

    public List<Hotel> findHotelByCity(String city) throws BadRequestException {
        return super.findHotelByCity(city);
    }

    public List<Room> findRooms(Filter filter) throws BadRequestException {
        return super.findRooms(filter);
    }

    public Order bookRoom(long roomId, long userId, long hotelId, Date dateFrom, Date dateTo) {

    }

    public void cancelReservation(long roomId, long userId) {

    }

    public User registerUser(User user) {
        return user;
    }

    public User login(String userName, String password) {
        return null;
    }

    public User logout(User user) {
        return null;
    }
}
