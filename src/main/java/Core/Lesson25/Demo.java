package Core.Lesson25;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) throws Exception {
        GeneralDAO<String> generalDAO = new GeneralDAO<>();
        GeneralDAO<Integer> generalDAO1 = new GeneralDAO<>();
        GeneralDAO<Long> generalDAO2 = new GeneralDAO<>();

        generalDAO.save("dfdfdf");
        generalDAO1.save(254);
        generalDAO2.save(125L);

        System.out.println(Arrays.toString(generalDAO2.getAll()));
    }

}
