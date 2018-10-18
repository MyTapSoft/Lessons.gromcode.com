package Core.Lesson35.Demo;

import Core.Lesson35.Controller.UserController;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.User;
import Core.Lesson35.Model.UserType;

import java.io.FileNotFoundException;

public class UserDemo {
    public static void main(String[] args) throws InternalServerException, BadRequestException, FileNotFoundException {
        User user = new User("iyfufklf", "mbghfhg", "USA", UserType.USER);
        User user1 = new User("ala", "dfgfd3d", "USA", UserType.USER);
        User user2 = new User("ara", "dfgfd3d", "USA", UserType.USER);
        User user4 = new User("ana", "sdf44", "USA", UserType.USER);
        UserController controller = new UserController();

        controller.registerUser(user);
        controller.registerUser(user1);
        controller.registerUser(user2);
        controller.registerUser(user4);


    }
}
