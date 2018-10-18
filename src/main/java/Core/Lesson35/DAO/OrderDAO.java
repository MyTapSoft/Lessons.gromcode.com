package Core.Lesson35.DAO;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;
import Core.Lesson35.Model.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

public class OrderDAO extends GeneralDAO<Order> {
    private static UserDAO userDAO = new UserDAO();
    private static RoomDAO roomDAO = new RoomDAO();

    public Order bookRoom(long roomId, long userId, long hotelId) throws BadRequestException, InternalServerException, FileNotFoundException {
        for (Order order : readObjectsFromFile()) {
            if (order.getRoom().getId() == roomId && order.getRoom().getHotel().getId() == hotelId && order.getUser().getId() == userId)
                throw new BadRequestException("Order already exist. ID: " + order.getId());
        }

        Calendar calendar = Calendar.getInstance();//Все эти действия - это просто гемморой с датой. Они особо не влияют ни на что. Но кинуть их после проверки я не могу - мне нужно сначала создать заказ.
        Date dateFrom = new Date();
        calendar.setTime(dateFrom);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date dateTo = calendar.getTime();
        int costRentPerOur = 10;


        Order result = new Order(userDAO.findById(userId), roomDAO.findById(roomId), dateFrom, dateTo, rentPrice(dateFrom, dateTo, costRentPerOur));
        return writeToDB(result);
    }

    public void cancelReservation(long roomId, long userId) throws BadRequestException, InternalServerException, FileNotFoundException {
        for (Order orders : readObjectsFromFile()) {
            if (orders.getUser().getId() == userId && orders.getRoom().getId() == roomId)
                deleteFromDB(orders.getId());
        }
    }

    private double rentPrice(Date dateFrom, Date dateTo, int pricePerOur) {
        double diffInDays = (dateTo.getTime() - dateFrom.getTime()) / (1000 * 60 * 60);
        return diffInDays * pricePerOur;
    }

    @Override
    public Order objectFromString(String line) throws BadRequestException, InternalServerException, FileNotFoundException {
        String[] fields = line.split(",");
        Order order;
        try {
            order = new Order(userDAO.findById(Long.parseLong(fields[1])), roomDAO.findById(Long.parseLong(fields[2])), new Date(Long.parseLong(fields[3])), new Date(Long.parseLong(fields[4])), Double.valueOf(fields[5]));

        } catch (Exception e) {
            throw new InternalServerException("Cant transfer string to Order object");
        }
        return order;
    }

    @Override
    public File DB() throws FileNotFoundException {
        File file = new File("C:\\Users\\Александр\\Desktop\\Java\\Gromcode\\OrderDB.txt");
        if (!file.exists()) throw new FileNotFoundException("Wrong path to Order DB: " + file.getAbsolutePath());
        return file;
    }
}
