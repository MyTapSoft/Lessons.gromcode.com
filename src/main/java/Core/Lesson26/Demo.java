package Core.Lesson26;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(i);
            System.out.println(list.get(i));
        }
        list.set(0,777);
        System.out.println(list.add(0));


    }
}
