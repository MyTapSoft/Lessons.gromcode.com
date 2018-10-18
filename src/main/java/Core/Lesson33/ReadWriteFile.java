package Core.Lesson33;

import java.io.*;
import java.util.ArrayList;

public class ReadWriteFile {

    public static void readFile(String path) throws Exception {
        validate(path);
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        try {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new IOException("Cant read file from path: " + path);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.err.println("Cant close stream");
            }
        }
    }

    public static void writeFile(String path, String content) throws Exception {
        validate(path);
        if (content == null) {
            System.err.println("Content is null");
            return;
        }
        PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(path, true)), true);
        try {
            fileWriter.write(content);
        } catch (Exception e) {
            throw new IOException("Cant write file: " + path);
        } finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                System.err.println("Cant close stream");
            }
        }
    }

    public static void writeToFileFromConsole(String path) throws Exception {
        validate(path);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        ArrayList<String> userWords = new ArrayList<>();
        System.out.println("Enter file content to write in the file: ");
        try {
            while (!(line = in.readLine()).contentEquals("wr")) {
                userWords.add(line);
            }
            for (String word : userWords) {
                out.append(word);
            }

        } catch (IOException e) {
            System.err.println("Can't write to file: " + path);
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                System.err.println("Cant close stream");
            }
        }
    }

    public static void readFileByConsolePath() {
        System.out.println("Please, enter file path to read: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            readFile(in.readLine());
        } catch (Exception e) {
            System.err.println("Cant read from console...");
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                System.err.println("Cant close stream");
            }
        }
    }

    private static void validate(String filePath) throws Exception {
        if (filePath == null) {
            System.err.println("File is null");
            return;
        }
        File file = new File(filePath);
        if (!file.canRead()) throw new Exception("File doesn't have permissions to be read" + file);
        if (!file.exists()) throw new FileNotFoundException("File doesn't exist" + file);
    }
}
