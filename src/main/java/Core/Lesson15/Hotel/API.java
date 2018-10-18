package Core.Lesson15.Hotel;

public interface API {
     Room[] findRooms(int price, int persons, String city, String hotel);
     Room[] getAll();
}
