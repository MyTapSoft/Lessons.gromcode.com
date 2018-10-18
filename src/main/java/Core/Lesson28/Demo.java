package Core.Lesson28;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Capability> list = new LinkedList<>();
        Capability capability = new Capability(10, null, "absd", true, new Date());
        Capability capability1 = new Capability(100, "rtesd", "sd", false, new Date());
        Capability capability2 = new Capability(15, "sfgdd", "sd", true, new Date());
        Capability capability3 = new Capability(1440, null, "sd", false, new Date());
        Capability capability4 = new Capability(1000, "shggsdsd", "sd", true, new Date());

        for (int i = 0; i < 1000; i++) {
            list.add(capability);
            list.add(capability1);
            list.add(capability2);
            list.add(capability3);
            list.add(capability4);
        }
        list.sort(new FullComparator());


        for (Capability c : list
                ) {
            System.out.println(c);

        }
    }
}
