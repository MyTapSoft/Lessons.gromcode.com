package Core.Lesson29;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {
    public void useHashSet(){
        Set<Order> hashSet = new HashSet<>();
        Order order = new Order(10,100,"df","er","dgg");
        hashSet.add(order);
        hashSet.remove(order);
    }
}
