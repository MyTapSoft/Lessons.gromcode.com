package Core.Lesson24;

public class Sys {
    private int fileCoint;
    private String location;

    public Sys(int fileCoint, String location) {
        this.fileCoint = fileCoint;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "fileCoint=" + fileCoint +
                ", location='" + location + '\'' +
                '}';
    }
}
