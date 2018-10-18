package Core.Lesson15.Hotel;

public class BookingComAPI implements API {
    private Room[] rooms;

    public BookingComAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {


        if (price >= 0 && persons >= 0 &&
                city != null && hotel != null) {
            int roomCount = 0;
            for (Room room : rooms) { //ищем кол-во комнат, которые необходимо вернуть
                if (room.getPrice() <= price + 100 && room.getPrice() >= price - 100 &&
                        persons == room.getPersons() && city.equalsIgnoreCase(room.getCityName()) &&
                        hotel.equalsIgnoreCase(room.getHotelName())) {
                    roomCount++;
                }
            }
            Room[] result = new Room[roomCount];
            int count = 0;
            for (Room room : rooms) { //создаем массив, длинна которого равна roomCount и заносим в него комнаты
                if (room.getPrice() <= price + 100 && room.getPrice() >= price - 100 &&
                        persons == room.getPersons() && city.equalsIgnoreCase(room.getCityName()) &&
                        hotel.equalsIgnoreCase(room.getHotelName())) {
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
