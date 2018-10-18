package Core.Lesson26;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
    public  List useList(){
        ArrayList<Order> list = new ArrayList<>();
        list.add(new Order(1,2,"1","1","1"));
        list.add(1,new Order(2,2,"1","1","1"));
        list.remove(0);
        list.set(0,new Order(1,2,"1","1","1"));
        list.add(new Order(1,2,"1","1","1"));
        list.clear();
        return list;

    }


}
