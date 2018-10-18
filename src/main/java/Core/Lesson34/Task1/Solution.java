package Core.Lesson34.Task1;

import java.io.*;

public class Solution {
    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);
        writeToFile(fileFromPath, fileToPath);
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        if (fileFromPath == null || fileToPath == null) {
            System.err.println("One of files path or both of them are null");
            return;
        }
        File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
        if (!fileFrom.exists()) throw new FileNotFoundException("File doesn't exist" + fileFrom);
        if (!fileTo.exists()) throw new FileNotFoundException("File doesn't exist" + fileTo);
        if (!fileFrom.canRead()) throw new Exception("File doesn't have permissions to be read" + fileFrom);
        if (!fileTo.canWrite()) throw new Exception("File doesn't have permissions to be write" + fileTo);

    }

    private static void writeToFile(String fileFromPath, String fileToPath) {
        try (BufferedReader in = new BufferedReader(new FileReader(fileFromPath)); BufferedWriter out = new BufferedWriter(new FileWriter(fileToPath))) {
            String line;
            while ((line = in.readLine()) != null) {
                out.write(line + "\n");
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
    }
}
