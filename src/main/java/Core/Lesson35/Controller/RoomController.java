package Core.Lesson35.Controller;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Filter;
import Core.Lesson35.Model.Room;
import Core.Lesson35.Service.RoomService;

import java.io.FileNotFoundException;
import java.util.List;

public class RoomController {
    private RoomService roomService = new RoomService();

    public Room addRoom(Room room) throws InternalServerException, BadRequestException, FileNotFoundException {
        return roomService.addRoom(room);
    }

    public boolean deleteRoom(long roomId) throws InternalServerException, BadRequestException, FileNotFoundException {
        return roomService.deleteRoom(roomId);
    }

    public List<Room> filteredBy(Filter filter) throws BadRequestException, InternalServerException, FileNotFoundException {
        return roomService.filteredBy(filter);
    }
}
