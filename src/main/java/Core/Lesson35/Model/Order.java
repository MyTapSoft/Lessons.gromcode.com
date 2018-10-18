package Core.Lesson35.Model;

import Core.Lesson35.DAO.IdEntity;

import java.util.Date;

public class Order extends IdEntity {
    private long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    public Order(User user, Room room, Date dateFrom, Date dateTo, double moneyPaid) {
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
        id = idGenerator();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(getId()) + "," + String.valueOf(getUser().getId()) + "," + String.valueOf(getRoom().getId()) + "," + String.valueOf(getDateFrom().getTime()) + "," + String.valueOf(getDateTo().getTime()) + "," + String.valueOf(getMoneyPaid());

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    private long idGenerator() {
        return user.getId() + room.getId() - dateTo.getTime() + dateFrom.getTime();
    }
}