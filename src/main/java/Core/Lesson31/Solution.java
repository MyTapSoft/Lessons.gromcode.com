package Core.Lesson31;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<Character, Integer> countSymbols(String text) {
        Map<Character, Integer> result = new HashMap<>();
        if (text != null) {
            char[] chars = text.toCharArray();
            int count = 0;


            for (char ch : chars) {
                for (char chArray : chars) {
                    if (ch == chArray) {
                        count++;
                    }
                }
                if (ch != 32) result.put(ch, count);
                count = 0;
            }

        }
        return result;
    }

    public static Map<String, Integer> words(String text) {
        Map<String, Integer> result = new HashMap<>();
        if (text != null) {
            String[] words = text.split(" ");
            int count = 0;
            for (String word : words) {
                if (word.length() < 2) {
                    words[count] = null;
                }
                count++;
            }
            count = 0;
            for (String word : words) {
                for (String wordArray : words) {
                    if (word != null && wordArray != null) {
                        if (word.equalsIgnoreCase(wordArray)) {
                            count++;

                        }
                    }
                }
                if (word != null) result.put(word, count);
                count = 0;
            }

        }
        return result;
    }
}
