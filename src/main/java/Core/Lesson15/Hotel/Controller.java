package Core.Lesson15.Hotel;

public class Controller {
    private API[] apis;

    public Controller(API[] apis) {
        this.apis = apis;
    }

    public Room[] requestRooms(int price, int persons, String city, String hotel) {
        for (API api : apis) {
            api.findRooms(price, persons, city, hotel);
        }
        return null;
    }



    public Room[] check (API api1, API api2){
        Room[] first = api1.getAll();
        Room[] second = api2.getAll();
        Room[] result;
        int count = 0, index = 0;

        for (Room aFirst : first) {
            for (Room aSecond : second) {
                if (aFirst.equals(aSecond)) {
                    count++;
                }
            }
        }

        result = new Room[count];

        for (Room aFirst : first) {
            for (Room aSecond : second) {
                if (aFirst.equals(aSecond)) {
                    result[index] = aSecond;
                    index++;
                }
            }
        }

        return result;
    }
}

