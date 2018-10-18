package Core.Lesson35.Demo;

import Core.Lesson35.Controller.OrderController;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Hotel;
import Core.Lesson35.Model.Room;
import Core.Lesson35.Model.User;
import Core.Lesson35.Model.UserType;

import java.io.FileNotFoundException;
import java.util.Date;

public class OrderDemo {
    public static void main(String[] args) throws BadRequestException, InternalServerException, FileNotFoundException {
        Hotel hotel = new Hotel("Califa", "USA", "Denver", "Poliddy 46");
        User user = new User("iyfufklf", "mbghfhg", "USA", UserType.USER);
        Room room = new Room(25, 2, false, true, new Date(), hotel);
        OrderController controller = new OrderController();

        controller.bookRoom(room.getId(), user.getId(), hotel.getId());
        controller.cancelReservation(room.getId(), user.getId());

    }
}
