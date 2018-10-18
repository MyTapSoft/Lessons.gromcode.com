package Core.Lesson35.DAO;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.User;
import Core.Lesson35.Model.UserType;

import java.io.File;
import java.io.FileNotFoundException;

public class UserDAO extends GeneralDAO<User> {

    public User registerUser(User user) throws InternalServerException, BadRequestException, FileNotFoundException {
        return writeToDB(user);
    }

    public User login(String userName, String password) throws InternalServerException, BadRequestException, FileNotFoundException {
        for (User user : readObjectsFromFile()) {
            if (user.getName().contentEquals(userName) && user.getPassword().contentEquals(password)) {
                if (user.isLoginStatus()) {
                    throw new BadRequestException("User already logged in. ID: " + user.getId());
                } else {
                    deleteFromDB(user.getId());
                    user.setLoginStatus(true);
                    registerUser(user);
                    return user;
                }
            }
        }
        throw new BadRequestException("There's no user with name: " + userName + " and password: " + password);
    }

    public User logout(User user) throws BadRequestException, InternalServerException, FileNotFoundException {
        deleteFromDB(user.getId());
        user.setLoginStatus(false);
        registerUser(user);
        return user;
    }

    @Override
    public User objectFromString(String line) throws InternalServerException {
        String[] fields = line.split(",");
        User result;
        try {
            result = new User(fields[1], fields[2], fields[3], UserType.valueOf(fields[4]));
            result.setLoginStatus(Boolean.valueOf(fields[5]));
        } catch (Exception e) {
            throw new InternalServerException("Cant transfer string to User object: " + line);
        }
        return result;
    }

    @Override
    public File DB() throws FileNotFoundException {
        File file = new File("C:\\Users\\Александр\\Desktop\\Java\\Gromcode\\UserDB.txt");
        if (!file.exists()) throw new FileNotFoundException("Wrong path to User DB: " + file.getAbsolutePath());
        return file;
    }

    public User findById(long id) throws BadRequestException, InternalServerException, FileNotFoundException {
        return super.findById(id);
    }

}
