package Core.Lesson24;

public class ToolDAO {

    private Tool[] tools = new Tool[5];

    public Tool save(Tool tool) {
        int index = 0;
        for (Tool t : tools) {
            if (t == null) {
                tools[index] = t;
                return tools[index];
            }
            index++;
        }
        return null;
    }
}
