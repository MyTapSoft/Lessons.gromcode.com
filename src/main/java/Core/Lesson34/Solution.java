package Core.Lesson34;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class Solution {
    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);
        writeToFile(fileFromPath, fileToPath);
    }

    public static void transferFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);
        writeToFile(fileFromPath, fileToPath);
        cleanFile(fileFromPath);
    }

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        //Логика метода:
        // Пункт 1: записать весь текст в StringBuilder.
        // Пункт 2: разбить текст на предложения по точке.
        // Пункт 3: Разбить предложения на слова
        // Пунки 4: Найти ключевое слово в предложении - записать его во второй файл и удалить из массива с предложенями
        // Пункт 5: Проверить было ли найдено ключевое слово: если да - перезаписать первый файл с учетом данных.
        validate(fileFromPath, fileToPath, word);
        File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
        boolean res = false;//Булеан служит для проверки ключевой фразы: если она есть - тогда первый файл перезаписывается.
        int index = 0;//Индекс нужен для двумерноо массива. Если ключевое слово найдено - нужно записать предложение и удалить его по индексу в массиве.
        // Удаление необходимо для того, чтобы в дальнейшем перезаписать первый файл.

        /*Пункт 1*/
        try (BufferedReader in = new BufferedReader(new FileReader(fileFrom)); PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileTo, true)), true)) {
            String line;
            StringBuilder textFromFile = new StringBuilder();
            while ((line = in.readLine()) != null) {
                textFromFile.append(line).append(" ");
            }

            /*Пункт 2*/
            String[] sentencesByPoint = textFromFile.toString().split("\\."), words;//Я не знаю как в регулярке захватить точку. В идеале оно должно разбивать до точки включая ее. А сейчас оно точку не забирает.

            for (String sentence : sentencesByPoint) {
                /*Пункт 3*/
                words = sentence.split(" ");
                for (String oneWord : words) {
                    /*Пункт 4*/
                    if (oneWord.contentEquals(word) && sentence.length() > 10) {
                        out.append(sentence).append(".");
                        sentencesByPoint[index] = null;
                        res = true;
                        break;
                    }
                }
                index++;
            }
            /*Пункт 2*/
            if (res) {
                cleanFile(fileFromPath);
                writeToFile(fileFromPath, sentencesByPoint);
            }
        }
    }

    public static void copyFileContentApacheIO(String fileFromPath, String fileToPath) throws Exception {
        if (fileToPath == null) {
            System.err.println("fileToPath is null");
            return;
        }
        validate(fileFromPath);
        try {
            File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
            if (!fileTo.exists()) fileTo.mkdirs();
            if (!fileTo.canWrite()) throw new Exception("File doesn't have permissions to be write" + fileTo);
            FileUtils.copyFile(fileFrom, fileTo);
        } catch (IOException e) {
            System.err.println("Cant transfer file data");
        }

    }

    private static void cleanFile(String filePath) {
        File file = new File(filePath);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)), true)) {
            out.write("");
        } catch (IOException e) {
            System.err.println("Cant clean file");
        }
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        if (fileFromPath == null || fileToPath == null) {
            System.err.println("One of files path or both of them are null");
            return;
        }
        File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
        if (!fileFrom.canRead()) throw new Exception("File doesn't have permissions to be read" + fileFrom);
        if (!fileTo.canWrite()) throw new Exception("File doesn't have permissions to be write" + fileTo);
        if (!fileFrom.exists()) throw new FileNotFoundException("File doesn't exist" + fileFrom);
        if (!fileTo.exists()) throw new FileNotFoundException("File doesn't exist" + fileTo);
    }

    private static void validate(String filePath) throws Exception {
        if (filePath == null) {
            System.err.println("One of files path or both of them are null");
            return;
        }
        File file = new File(filePath);
        if (!file.canRead()) throw new Exception("File doesn't have permissions to be read" + file);
        if (!file.exists()) throw new FileNotFoundException("File doesn't exist" + file);
    }

    private static void validate(String fileFromPath, String fileToPath, String word) throws Exception {
        if (word == null) {
            System.err.println("Keyword are null");
            return;
        }
        validate(fileFromPath, fileToPath);
    }

    private static void writeToFile(String fileFromPath, String fileToPath) {
        File fileFrom = new File(fileFromPath), fileTo = new File(fileToPath);
        try (BufferedReader in = new BufferedReader(new FileReader(fileFrom)); PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileTo, true)), true)) {
            String line;
            while ((line = in.readLine()) != null) {
                out.append(line);
                out.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Can't read file");
        }
    }

    private static boolean writeToFile(String filePath, String[] text) {
        File file = new File(filePath);
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)), true)) {
            for (String sentences : text) {
                if (sentences != null)
                    out.append(sentences).append(". ");
            }
        } catch (IOException e) {
            System.err.println("Cant read file");
        }
        return true;
    }
}