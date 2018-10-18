package Core.Lesson35.DAO;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Filter;
import Core.Lesson35.Model.Hotel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    public Hotel findHotelByName(String name) throws InternalServerException, BadRequestException, FileNotFoundException {
        for (Hotel hotel : readObjectsFromFile()) {
            System.out.println(hotel);
            if (hotel.getName().contentEquals(name)) {
                return hotel;
            }
        }
        throw new BadRequestException("There's no hotel with name: " + name);
    }

    public Hotel findHotelByCity(String city) throws InternalServerException, BadRequestException, FileNotFoundException {
        for (Hotel hotel : readObjectsFromFile()) {
            if (hotel.getCity().contentEquals(city)) {
                return hotel;
            }
        }
        throw new BadRequestException("There's no hotel with city: " + city);
    }

    public Hotel addHotel(Hotel hotel) throws InternalServerException, BadRequestException, FileNotFoundException {
        return writeToDB(hotel);
    }

    public boolean deleteHotel(long hotelId) throws InternalServerException, BadRequestException, FileNotFoundException {
        return deleteFromDB(hotelId);
    }


    public Hotel findById(long id) throws BadRequestException, InternalServerException, FileNotFoundException {
        return super.findById(id);
    }

    @Override
    public Hotel objectFromString(String line) throws InternalServerException {
        String[] fields = line.split(",");
        Hotel hotel;
        try {
            hotel = new Hotel(fields[1], fields[2], fields[3], fields[4]);

        } catch (Exception e) {
            throw new InternalServerException("Cant transfer string to Hotel object");
        }
        return hotel;
    }

    @Override
    public File DB() throws FileNotFoundException {
        File file = new File("C:\\Users\\Александр\\Desktop\\Java\\Gromcode\\HotelDB.txt");
        if (!file.exists()) throw new FileNotFoundException("Wrong path to Hotel DB: " + file.getAbsolutePath());
        return file;

    }

    public List<Hotel> filteredBy(Filter filter) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (filter.getCountry() == null || filter.getCity() == null) throw new BadRequestException("Filter is null");
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : readObjectsFromFile()) {
            if (hotel.getCountry().equalsIgnoreCase(filter.getCountry()) && hotel.getCity().equalsIgnoreCase(filter.getCity())) {
                result.add(hotel);
            }
        }
        return result;
    }

}
