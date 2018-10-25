package JDBC.Lesson7;

import JDBC.Lesson7.DAO.HotelDAO;
import JDBC.Lesson7.DAO.RoomDAO;
import JDBC.Lesson7.Model.Hotel;
import JDBC.Lesson7.Model.Room;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.setCity("NY");
        hotel.setCountry("USA");
        hotel.setName("Hotel Paradise");
        hotel.setStreet("Washington str");

        Room room = new Room();
        room.setBreakfastIncluded(1);
        room.setDateAvailableFrom(new Date());
        room.setHotel(hotel);
        room.setNumberOfGuests(100);
        room.setPetsAllowed(0);
        room.setPrice(10.0);

        HotelDAO.save(hotel);
        RoomDAO.save(room);

    }
}
