package Core.Lesson35.Model;

import Core.Lesson35.DAO.IdEntity;

public class Hotel extends IdEntity {
    private long id;
    private String name;
    private String country;
    private String city;
    private String street;

    public Hotel(String name, String country, String city, String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        id = idGenerator();
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(getId()) + "," + getName() + "," + getCountry() + "," + getCity() + "," + getStreet();

    }

    private long idGenerator() {
        StringBuilder builder = new StringBuilder();
        long result;
        char[] value = name.toCharArray();
        for (char ch : value) {
            builder.append((int) ch);
        }

        while (builder.length() > 18) {
            builder.deleteCharAt(builder.length() - 1);
        }
        result = Long.parseLong(builder.toString());
        value = (city + country + name + street).toCharArray();
        for (char ch : value) {
            result += ch;
        }
        return result;
    }
}
