package JDBC.Lesson8.Model;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "HOTEL")
public class Hotel {
    @Id
    @SequenceGenerator(name = "HT_SEQ", sequenceName = "HOTEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "HT_SEQ")
    @Column(name = "ID")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @OneToMany(mappedBy = "POST", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
