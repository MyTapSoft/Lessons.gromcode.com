package JDBC.Lesson7.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    private long id;
    @Column(name = "NUMBER_OF_GUESTS")
    private int numberOfGuests;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "BREAKFAST_INCLUDED")
    private int breakfastIncluded;
    @Column(name = "PETS_ALLOWED")
    private int petsAllowed;
    @Column(name = "DATE_AVAILABLE_FORM")
    private Date dateAvailableFrom;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID")
    @MapsId
    private Hotel hotel;


    public long getId() {
        return id;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public int getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public int getPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(int breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(int petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", hotel=" + hotel +
                '}';
    }
}
