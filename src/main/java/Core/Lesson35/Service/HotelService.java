package Core.Lesson35.Service;

import Core.Lesson35.DAO.HotelDAO;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Filter;
import Core.Lesson35.Model.Hotel;

import java.io.FileNotFoundException;
import java.util.List;

public class HotelService extends HotelDAO {

    public Hotel findHotelByName(String name) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (name == null) return null;
        return super.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (city == null) return null;
        return super.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws InternalServerException, BadRequestException, FileNotFoundException {
        if (hotel == null || hotel.getCity() == null || hotel.getCountry() == null || hotel.getName() == null || hotel.getStreet() == null)
            throw new InternalServerException("Hotel is null");

        if (findById(hotel.getId()) != null) {
            throw new BadRequestException("Hotel already exist. hotel ID: " + hotel.getId());
        }
        return super.addHotel(hotel);
    }

    public boolean deleteHotel(long hotelId) throws InternalServerException, BadRequestException, FileNotFoundException {
        if (findById(hotelId) == null) {
            throw new BadRequestException("Hotel doesn't exist. hotel ID: " + hotelId);
        }
        return super.deleteHotel(hotelId);
    }

    public List<Hotel> filteredBy(Filter filter) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (filter == null) throw new BadRequestException("Filter is null");
        return super.filteredBy(filter);
    }

}
