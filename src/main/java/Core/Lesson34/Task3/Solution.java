package Core.Lesson34.Task3;

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        validate(fileFromPath, fileToPath, word);
        boolean res = false;
        int index = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(fileFromPath)); PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileToPath, true)), true)) {
            String line;
            StringBuilder textFromFile = new StringBuilder();
            while ((line = in.readLine()) != null) {
                textFromFile.append(line);
            }

            String[] separateSentences = textFromFile.toString().split("\\."), words;
            for (String sentence : separateSentences) {
                words = sentence.split(" ");
                for (String oneWord : words) {
                    if (oneWord.contentEquals(word) && sentence.length() > 10) {
                        out.append(sentence).append(".");
                        separateSentences[index] = null;
                        res = true;
                        break;
                    }
                }
                index++;
            }
            if (res) {
                cleanFile(fileFromPath);
                writeToFile(fileFromPath, separateSentences);
            }
       }
    }

    public static void transferSentences2(String fileFromPath, String fileToPath, String word) throws Exception {
        validate(fileFromPath, fileToPath, word);
        File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
        ArrayList<String> fileValue = new ArrayList<>();
        String[] words, sentences;

        try (BufferedReader in = new BufferedReader(new FileReader(fileFrom)); PrintWriter to = new PrintWriter(new BufferedWriter(new FileWriter(fileTo, true)), true); PrintWriter from = new PrintWriter(new BufferedWriter(new FileWriter(fileFrom,true)), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                sentences = line.split("\\.");
                for (String sentence : sentences) {
                    fileValue.add(sentence);
                    words = sentence.split(" ");
                    for (String oneWord : words) {
                        if (oneWord.contentEquals(word)) {
                            to.print(sentence + ".");
                            fileValue.remove(sentence);
                            break;
                        }
                    }
                }
            }
            cleanFile(fileFromPath);
            for (String newData : fileValue) {
                from.print(newData + ".");
            }

        }

    }

    private static void cleanFile(String filePath) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)), true)) {
            out.write("");
        } catch (IOException e) {
            System.err.println("Cant clean file");
        }
    }

    private static void validate(String fileFromPath, String fileToPath, String word) throws Exception {
        if (fileFromPath == null || fileToPath == null || word == null) {
            System.err.println("One of files path or both of them are null");
            return;
        }
        File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
        if (!fileFrom.exists()) throw new FileNotFoundException("File doesn't exist" + fileFrom);
        if (!fileTo.exists()) throw new FileNotFoundException("File doesn't exist" + fileTo);
        if (!fileFrom.canRead()) throw new Exception("File doesn't have permissions to be read" + fileFrom);
        if (!fileTo.canWrite()) throw new Exception("File doesn't have permissions to be write" + fileTo);

    }

    private static void writeToFile(String filePath, String[] text) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String sentences : text) {
                if (sentences != null)
                    out.append(sentences).append(". ");
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
    }
}
