package Core.Lesson32.ReadName;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        ReadFromKeyboard readFromKeyboard = new ReadFromKeyboard();
        readFromKeyboard.readKeyboardWithIOStream();
        readFromKeyboard.readKeyboardWithScanner();
    }
}
