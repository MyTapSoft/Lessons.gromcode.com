package JDBC.Lesson8.Model;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "ORDER")
public class Order {
    @Id
    @SequenceGenerator(name = "HT_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "HT_SEQ")
    @Column(name = "ID")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User userOrdered;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID")
    private Room room;
    @Column(name = "DATE_FROM")
    private Date dateFrom;
    @Column(name = "DATE_TO")
    private Date dateTo;
    @Column(name = "MONEY_PAID")
    private double moneyPaid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserOrdered() {
        return userOrdered;
    }

    public void setUserOrdered(User userOrdered) {
        this.userOrdered = userOrdered;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userOrdered=" + userOrdered +
                ", room=" + room +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", moneyPaid=" + moneyPaid +
                '}';
    }
}
