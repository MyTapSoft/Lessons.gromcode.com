package Core.Lesson17;

public class Solution {
    public static void main(String[] args) {
        String s = " ff   ff  w  ff w w3w ";
        System.out.println(minWord(s));
        System.out.println(maxWord(s));
        System.out.println(countWords(s));
        System.out.println(mostCountedWord(s));
    }

    private static String maxWord(String input) {
        String[] words = input.split(" ");
        String result = findFirstWord(words);

        for (String s : words) {
            if (!s.isEmpty() && isLetters(s) && s.length() >= result.length()) {
                result = s;
            }
        }


        return result;
    }

    private static String minWord(String input) {
        String[] words = input.split(" ");
        String result = findFirstWord(words);

        for (String s : words) {
            if (!s.isEmpty() && isLetters(s) && s.length() <= result.length()) {
                result = s;
            }
        }
        return result;
    }

    private static boolean isLetters(String word) {
        char[] result = word.toCharArray();
        for (char c : result) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }


    private static String findFirstWord(String[] words) {
        for (String s : words) {
            if (!s.isEmpty() && isLetters(s)) {
                return s;
            }
        }
        return null;
    }

    private static int countWords(String input) {
        String[] words = input.split(" ");
        int result = 0;
        for (String s : words) {
            if (!s.isEmpty() && isLetters(s)) {
                result++;
            }

        }
        return result;
    }

    private static String mostCountedWord(String input) {
        String[] words = input.split(" ");
        String result = null;
        int count = 0;
        int count2 = 0;
        for (String s : words) {
            if (!s.isEmpty() && isLetters(s)) {
                for (String s2 : words) {
                    if (!s2.isEmpty() && isLetters(s2)) {
                        if (s.equalsIgnoreCase(s2)) {
                            count++;
                        }
                    }
                }
                if (count > count2) {
                    result = s;
                    count2 = count;
                }
                count = 0;
            }
        }
        return result;
    }

    private static boolean validate(String adress) {
        if (!adress.toLowerCase().startsWith("http://") && !adress.toLowerCase().startsWith("https://")) {
            return false;
        }
        if (!adress.toLowerCase().endsWith(".com") && !adress.toLowerCase().endsWith(".org") && !adress.toLowerCase().endsWith(".net")) {
            return false;
        }
        adress = adress.replace("www.", "");
        adress = isValid(adress, new String[]{"http://", "https://"});
        adress = isValid(adress, new String[]{".net", ".com", ".org"});
        return adress != null && isLetters(adress);
    }

    private static String isValid(String target, String[] replacecement) {
        for (String s : replacecement) {
            if (target.contains(s)) {
                target = target.replace(s, "");
                return target;
            }
        }
        return null;
    }

}

