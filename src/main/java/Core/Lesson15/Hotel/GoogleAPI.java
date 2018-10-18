package Core.Lesson15.Hotel;


import java.util.Date;

public class GoogleAPI implements API {
    private Room[] rooms;

    public GoogleAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {

        Room userRequestRoom = new Room(7987987,price,persons,new Date(), hotel, city);


        if (price >= 0 && persons >= 0 &&
                city != null && hotel != null) {
            int roomCount = 0;
            for (Room room : rooms) { //ищем кол-во комнат, которые необходимо вернуть
                if (room.equals(userRequestRoom)) {
                    roomCount++;
                }
            }
            Room[] result = new Room[roomCount];
            int count = 0;

            for (Room room : rooms) {  //создаем массив, длинна которого равна roomCount и заносим в него комнаты
                if (room.equals(userRequestRoom)) {

                    result[count] = room;
                    count++;
                }
            }
            return result;
        }
        return null;
    }

    @Override
    public Room[] getAll() {
        return rooms;
    }

}
