package Core.Lesson35.Demo;

import Core.Lesson35.Controller.RoomController;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Hotel;
import Core.Lesson35.Model.Room;

import java.io.FileNotFoundException;
import java.util.Date;

public class RoomDemo {
    public static void main(String[] args) throws InternalServerException, BadRequestException, FileNotFoundException {
        Hotel hotel = new Hotel("Califa", "USA", "Denver", "Poliddy 46");
        RoomController controller = new RoomController();
        Room room = new Room(25, 2, false, true, new Date(), hotel);
        Room room1 = new Room(2, 12, true, false, new Date(), hotel);
        Room room2 = new Room(75, 125, false, true, new Date(), hotel);
        Room room3 = new Room(5, 46, true, false, new Date(), hotel);

        controller.addRoom(room);
        controller.addRoom(room1);
        controller.addRoom(room2);
        controller.addRoom(room3);


    }
}
