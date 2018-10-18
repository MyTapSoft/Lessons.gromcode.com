package Core.Lesson32.TenNumbers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {


    public int readNumbers() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] stringToInt = new int[0];
        for (int i = 2; i >= 0; i--) {
            try {
                stringToInt = parseStringToIntArray(in.readLine().split(" "));
                if (validator(stringToInt)){
                    readMessage(i);
                    continue;
                }
            } catch (Exception e) {
                if (i == 0) {
                    in.close();
                    throw new Exception("Your numbers are wrong. Number of attempts exceeded"); }
                readMessage(i);
                continue;
            }
            in.close();
            break;
        }
        return sum(stringToInt);
    }

    private void readMessage(int attempts) {
        System.out.println("Your numbers are wrong. You have " + attempts + " attempts to try again");
    }

    private int[] parseStringToIntArray(String[] inputValue) {
        int[] stringToInt = new int[inputValue.length];
        for (int i = 0; i < stringToInt.length; i++) {
            stringToInt[i] = Integer.parseInt(inputValue[i]);
        }
        return stringToInt;
    }

    private boolean validator(int[] inputValue) {
        if (inputValue.length != 10) return true;
        for (int num : inputValue) {
            if (num > 100) return true;
        }
        return false;
    }

    private int sum(int[] numbers) {
        int result = 0;
        for (int num : numbers) {
            result += num;
        }
        return result;
    }

}
