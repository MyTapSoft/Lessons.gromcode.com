package Core.Lesson35.Controller;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Order;
import Core.Lesson35.Service.OrderService;

import java.io.FileNotFoundException;

public class OrderController {
    OrderService orderService = new OrderService();

    public Order bookRoom(long roomId, long userId, long hotelId) throws BadRequestException, InternalServerException, FileNotFoundException {
        return orderService.bookRoom(roomId, userId, hotelId);
    }

    public void cancelReservation(long roomId, long userId) throws BadRequestException, InternalServerException, FileNotFoundException {
        orderService.cancelReservation(roomId, userId);
    }
}
