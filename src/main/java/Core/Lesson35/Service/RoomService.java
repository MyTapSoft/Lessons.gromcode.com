package Core.Lesson35.Service;

import Core.Lesson35.DAO.RoomDAO;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Filter;
import Core.Lesson35.Model.Room;

import java.io.FileNotFoundException;
import java.util.List;

public class RoomService extends RoomDAO {

    public Room addRoom(Room room) throws InternalServerException, BadRequestException, FileNotFoundException {
        if (room == null || room.getDateAvailableFrom() == null || room.getHotel() == null) return null;
        if (findById(room.getId()) != null) {
            throw new BadRequestException("Room already exist: ID " + room.getId());
        }
        return super.addRoom(room);
    }

    public boolean deleteRoom(long roomId) throws InternalServerException, BadRequestException, FileNotFoundException {
        if (findById(roomId) == null) {
            throw new BadRequestException("Room doesn't exist: ID " + roomId);
        }
        return super.deleteRoom(roomId);
    }

    public List<Room> filteredBy(Filter filter) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (filter == null) throw new BadRequestException("Filter is null");
        return super.filteredBy(filter);
    }


}
