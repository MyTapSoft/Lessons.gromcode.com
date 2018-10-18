package Core.Lesson27;


import java.util.LinkedList;
import java.util.List;

public class LinkedListTest {

    public List useList() {
        List <Order> list = new LinkedList<>();
        list.add(new Order(1,10,"currency", "name","ar"));
        list.add(0, new Order(1,10,"currency", "name","ar"));
        list.add(new Order(1,10,"currency", "name","ar"));
        list.add(0, new Order(1,10,"currency", "name","ar"));
        list.add(new Order(1,10,"currency", "name","ar"));
        list.add(0, new Order(1,10,"currency", "name","ar"));
        list.set(0,new Order(1,10,"currency", "name","ar"));
        list.remove(2);
        list.size();
        list.get(5);
        return list;

    }
}
