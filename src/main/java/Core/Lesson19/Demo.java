package Core.Lesson19;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        try {
            File file = new File(41, "File", ".mp3", 10);
            File file1 = new File(1, "File1", ".mp3", 10);
            File file2 = new File(4, "File2", ".mp3", 10);
            Storage storage = new Storage(1, new File[5], new String[]{".mp3", ".jpg"}, "Local", 308);
            Storage storage2 = new Storage(2, new File[5], new String[]{".mp3", ".jpg"}, "Local", 308);
            Storage storage3 = new Storage(2, new File[5], new String[]{".mp3", ".jpg"}, "Local", 3008);

            Controller controller = new Controller();

            controller.put(storage, file);
            controller.put(storage, file1);
            controller.put(storage, file2);
            System.out.println(Arrays.toString(storage.getFiles()));
            System.out.println(Arrays.toString(storage3.getFiles()));
            controller.transferAll(storage,storage3);
            System.out.println(Arrays.toString(storage3.getFiles()));



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
