package Core.Lesson35.Model;

import Core.Lesson35.DAO.IdEntity;

import java.util.Date;

public class Room extends IdEntity {
    private long id;
    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;


    public Room(int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
        id = idGenerator();
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(getId()) + "," + String.valueOf(getNumberOfGuests()) + "," + String.valueOf(getPrice()) + "," + String.valueOf(isBreakfastIncluded()) + "," + String.valueOf(isPetsAllowed() + "," + String.valueOf(getDateAvailableFrom().getTime())) + "," + String.valueOf(getHotel().getId());
    }

    private long idGenerator() {
        StringBuilder builder = new StringBuilder();
        for (char ch : (String.valueOf(numberOfGuests) + String.valueOf((int) price) + String.valueOf(dateAvailableFrom.getTime())).toCharArray()) {
            builder.append((int) ch);
        }

        while (builder.length() > 18) {
            builder.deleteCharAt(builder.length() - 1);
        }
        long result = Long.parseLong(builder.toString());

        for (char ch : (String.valueOf(numberOfGuests) + String.valueOf((int) price) + String.valueOf(breakfastIncluded) + String.valueOf(petsAllowed) + String.valueOf(hotel.getId())).toCharArray()) {
            result += ch;
        }
        return result;
    }
}
