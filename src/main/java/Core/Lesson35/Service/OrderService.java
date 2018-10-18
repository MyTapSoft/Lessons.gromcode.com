package Core.Lesson35.Service;

import Core.Lesson35.DAO.OrderDAO;
import Core.Lesson35.DAO.RoomDAO;
import Core.Lesson35.DAO.UserDAO;
import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Order;

import java.io.FileNotFoundException;

public class OrderService extends OrderDAO {
    private RoomDAO roomDAO = new RoomDAO();
    private UserDAO userDAO = new UserDAO();

    public Order bookRoom(long roomId, long userId, long hotelId) throws BadRequestException, InternalServerException, FileNotFoundException {
        nullValidator(roomId, userId);
        if (roomDAO.findById(roomId).getHotel().getId() != hotelId)
            throw new BadRequestException("Room with ID: " + roomId + " doesn't belong to hotel with ID: " + hotelId);
        return super.bookRoom(roomId, userId, hotelId);
    }

    public void cancelReservation(long roomId, long userId) throws BadRequestException, InternalServerException, FileNotFoundException {
        nullValidator(roomId, userId);
        super.cancelReservation(roomId, userId);
    }

    private void nullValidator(long roomId, long userId) throws BadRequestException, InternalServerException, FileNotFoundException {
        if (roomDAO.findById(roomId) == null) throw new BadRequestException("There's no room with ID: " + roomId);
        if (userDAO.findById(userId) == null) throw new BadRequestException("There's  no user with ID: " + roomId);
        if (!userDAO.findById(userId).isLoginStatus()) throw new BadRequestException("User is not logged in. ID: " + userId);
    }
}
