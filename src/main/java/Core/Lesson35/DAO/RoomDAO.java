package Core.Lesson35.DAO;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Filter;
import Core.Lesson35.Model.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomDAO extends GeneralDAO<Room> {
    private static HotelDAO hotelDAO = new HotelDAO();

    public Room addRoom(Room room) throws InternalServerException, BadRequestException, FileNotFoundException {
        return writeToDB(room);
    }

    public boolean deleteRoom(long roomId) throws InternalServerException, BadRequestException, FileNotFoundException {
        return deleteFromDB(roomId);
    }

    public Room findById(long roomId) throws InternalServerException, BadRequestException, FileNotFoundException {
        return super.findById(roomId);
    }

    @Override
    public Room objectFromString(String line) throws BadRequestException, InternalServerException, FileNotFoundException {
        String[] fields = line.split(",");
        Room room;
        if (hotelDAO.findById(Long.parseLong(fields[6])) == null)
            throw new InternalServerException("Hotel does'n exist. Room ID: " + fields[0] + ". Hotel ID: " + fields[6]);
        try {
            room = new Room(Integer.parseInt(fields[1]), Double.valueOf(fields[2]), Boolean.valueOf(fields[3]), Boolean.valueOf(fields[4]), new Date(Long.parseLong(fields[5])), hotelDAO.findById(Long.parseLong(fields[6])));

        } catch (Exception e){
            throw new InternalServerException("Cant transfer string to Room object: " + line);
        }
        return room;
    }

    @Override
    public File DB() throws FileNotFoundException {
        File file = new File("C:\\Users\\Александр\\Desktop\\Java\\Gromcode\\RoomDB.txt");
        if (!file.exists()) throw new FileNotFoundException("Wrong path to Room DB: " + file.getAbsolutePath());
        return file;
    }

    public List<Room> filteredBy(Filter filter) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (filter.getCountry() == null) throw new BadRequestException("Filter is null");
        List<Room> result = new ArrayList<>();
        for (Room room : readObjectsFromFile()) {
            if (room.getNumberOfGuests() == filter.getNumberOfGuests() && room.getDateAvailableFrom() == filter.getDateAvailableFrom()) {
                if (room.isPetsAllowed() == filter.isPetsAllowed() && room.isBreakfastIncluded() == filter.isBreakfastIncluded()) {
                    if (room.getHotel().getCountry().equalsIgnoreCase(filter.getCountry()) && room.getHotel().getCity().equalsIgnoreCase(filter.getCity())) {
                        if (room.getPrice() <= filter.getPrice()) {
                            result.add(room);
                        }
                    }
                }
            }
        }
        return result;
    }
}
