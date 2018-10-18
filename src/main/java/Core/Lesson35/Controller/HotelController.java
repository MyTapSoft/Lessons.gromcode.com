package Core.Lesson35.Controller;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Filter;
import Core.Lesson35.Model.Hotel;
import Core.Lesson35.Service.HotelService;

import java.io.FileNotFoundException;
import java.util.List;

public class HotelController {
    private HotelService hotelService = new HotelService();

    public Hotel findHotelByName(String name) throws BadRequestException, InternalServerException, FileNotFoundException {
        return hotelService.findHotelByName(name);
    }

    public Hotel findHotelByCity(String city) throws BadRequestException, InternalServerException, FileNotFoundException {
        return hotelService.findHotelByCity(city);
    }

    public Hotel addHotel(Hotel hotel) throws InternalServerException, BadRequestException, FileNotFoundException {
        return hotelService.addHotel(hotel);
    }

    public boolean deleteHotel(long hotelId) throws InternalServerException, BadRequestException, FileNotFoundException {
        return hotelService.deleteHotel(hotelId);
    }

    public List<Hotel> filteredBy(Filter filter) throws BadRequestException, InternalServerException, FileNotFoundException {
        return hotelService.filteredBy(filter);
    }
}
