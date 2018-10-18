package Core.Lesson15.Hotel;

public class TripAdvisorAPI implements API {
    private Room[] rooms;

    public TripAdvisorAPI(Room[] rooms) {
        this.rooms = rooms;
    }

    @Override
    public Room[] findRooms(int price, int persons, String city, String hotel) {

        if (price >= 0 && persons >= 0 &&
                city != null && hotel != null) {
            int roomCount = 0;
            for (Room room : rooms) {  //ищем кол-во комнат, которые необходимо вернуть
                if (price == room.getPrice() &&
                        room.getPersons() <= persons + 1 && room.getPersons() >= persons - 1 &&
                        city.equalsIgnoreCase(room.getCityName()) && hotel.equalsIgnoreCase(room.getHotelName())) {
                    roomCount++;
                }
            }

            Room[] result = new Room[roomCount];
            int count = 0;

            for (Room room : rooms) {  //создаем массив, длинна которого равна roomCount и заносим в него комнаты
                if (price == room.getPrice() &&
                        room.getPersons() <= persons + 1 && room.getPersons() >= persons - 1 &&
                        city.equalsIgnoreCase(room.getCityName()) && hotel.equalsIgnoreCase(room.getHotelName())) {
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
