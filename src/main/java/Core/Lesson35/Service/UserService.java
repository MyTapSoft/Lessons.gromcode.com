package Core.Lesson35.Service;

import Core.Lesson35.DAO.UserDAO;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.User;

import java.io.FileNotFoundException;

public class UserService extends UserDAO {

    public User registerUser(User user) throws InternalServerException, BadRequestException, FileNotFoundException {
        if (user == null || user.getType() == null || user.getCountry() == null || user.getName() == null || user.getPassword() == null) throw new BadRequestException("User is null");
        if (findById(user.getId()).getName() != null) throw new BadRequestException("User already exist. User ID: " + user.getId());
        return super.registerUser(user);
    }

    public User login(String userName, String password) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (userName == null || password == null) throw new BadRequestException("User or password are null!");
        return super.login(userName, password);//будет рекурсия без супер
    }

    public User logout(User user) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (user == null) throw new BadRequestException("User is null");
        if (!user.isLoginStatus()) throw new BadRequestException("User already offline. ID: " + user.getId());

        return super.logout(user);
    }

}
