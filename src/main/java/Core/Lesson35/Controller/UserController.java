package Core.Lesson35.Controller;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.User;
import Core.Lesson35.Service.UserService;

import java.io.FileNotFoundException;

public class UserController {
    private UserService userService = new UserService();

    public User registerUser(User user) throws InternalServerException, BadRequestException, FileNotFoundException {
        return userService.registerUser(user);
    }

    public User login(String userName, String password) throws InternalServerException, BadRequestException, FileNotFoundException {
        return userService.login(userName, password);
    }

    public User logout(User user) throws BadRequestException, InternalServerException, FileNotFoundException {
        return userService.logout(user);
    }
}
