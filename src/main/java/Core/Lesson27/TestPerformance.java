package Core.Lesson27;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class TestPerformance {
    public static void main(String[] args) {
        testAddMethod();
        testRemoveMethod();
        testGetMethod();

    }

    private static void testRemoveMethod() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i, String.valueOf(i));
        }
        Date start = new Date();
        for (int i = 0; i < 100000; i++) {
            arrayList.remove(50000);
        }
        Date finish = new Date();

        long diff = finish.getTime() - start.getTime();
        System.out.println(diff);

        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i, String.valueOf(i));
        }
        Date start1 = new Date();
        for (int i = 0; i < 100000; i++) {
            arrayList.remove(50000);
        }
        Date finish1 = new Date();

        diff = finish1.getTime() - start1.getTime();
        System.out.println(diff);
    }

    private static void testGetMethod() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i, String.valueOf(i));
        }
        Date start = new Date();
        for (int i = 0; i < 100000; i++) {
            arrayList.get(50000);
        }
        Date finish = new Date();

        long diff = finish.getTime() - start.getTime();
        System.out.println(diff);

        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i, String.valueOf(i));
        }
        Date start1 = new Date();
        for (int i = 0; i < 100000; i++) {
            arrayList.get(50000);
        }
        Date finish1 = new Date();

        diff = finish1.getTime() - start1.getTime();
        System.out.println(diff);


    }

    private static void testAddMethod() {
        ArrayList<String> arrayList = new ArrayList<>();
        Date start = new Date();
        for (int i = 0; i < 100000; i++) {
            arrayList.add(i, String.valueOf(i));
        }
        Date finish = new Date();
        long diff = finish.getTime() - start.getTime();

        System.out.println(diff);
        LinkedList<String> linkedList = new LinkedList<>();
        Date start1 = new Date();

        for (int i = 0; i < 100000; i++) {
            linkedList.add(i, String.valueOf(i));
        }

        Date finish1 = new Date();

        diff = finish1.getTime() - start1.getTime();
        System.out.println(diff);

    }
}
