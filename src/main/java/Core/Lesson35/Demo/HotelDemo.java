package Core.Lesson35.Demo;

import Core.Lesson35.Controller.HotelController;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Hotel;

import java.io.FileNotFoundException;

public class HotelDemo {
    public static void main(String[] args) throws BadRequestException, InternalServerException, FileNotFoundException {
        HotelController controller = new HotelController();
        Hotel hotel = new Hotel("Califa", "USA", "Denver", "Poliddy 46");
        Hotel hotel1 = new Hotel("sdfsdf", "USA", "Denccsfver", "Poliy 46");
        Hotel hotel2 = new Hotel("xcxc", "USA", "sdfv", "Poliy 46");
        Hotel hotel3 = new Hotel("fgfgvc", "USA", "eeedv", "Polidfy 46");

        controller.addHotel(hotel);
        controller.addHotel(hotel1);
        controller.addHotel(hotel2);
        controller.addHotel(hotel3);

    }
}
