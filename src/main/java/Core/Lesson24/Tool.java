package Core.Lesson24;

public class Tool {
    private String name;
    private String descriptoin;

    public Tool(String name, String descriptoin) {
        this.name = name;
        this.descriptoin = descriptoin;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "name='" + name + '\'' +
                ", descriptoin='" + descriptoin + '\'' +
                '}';
    }
}
