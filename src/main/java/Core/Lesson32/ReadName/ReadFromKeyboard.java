package Core.Lesson32.ReadName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFromKeyboard {

    public void readKeyboardWithScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = scanner.next();
        if (validate(name))
            System.out.println("Hello, " + name + "!");
        else System.out.println("Wrong name format");
    }

    public void readKeyboardWithIOStream() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your name");
        String name = in.readLine();
        if (validate(name))
            System.out.println("Hello, " + name + "!");
        else System.out.println("Wrong name format");
    }

    private boolean validate(String name) {
        if (name.split(" ").length > 1) return false;
        char[] value = name.toCharArray();
        for (char chars : value) {
            if (!Character.isLetter(chars) && Character.isDigit(chars))
                return false;
        }
        return true;
    }
}
