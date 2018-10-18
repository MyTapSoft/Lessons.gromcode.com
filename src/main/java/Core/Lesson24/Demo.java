package Core.Lesson24;

public class Demo {
    public static void main(String[] args) {
        GeneralDAO <Sys> generalDAO = new GeneralDAO<>();
        Sys system = new Sys(8,"dk");
        generalDAO.save(system);

        System.out.println(system);
        generalDAO.save(system);
        System.out.print(system);
    }

}
