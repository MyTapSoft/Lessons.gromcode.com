package Core.Lesson35.DAO;

import Core.Lesson35.Exceptions.BadRequestException;
import Core.Lesson35.Exceptions.InternalServerException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneralDAO<T extends IdEntity> {

    protected T writeToDB(T t) throws InternalServerException, FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(DB(), true)), true)) {
            writer.println(t.toString());
        } catch (IOException e) {
            throw new InternalServerException("Can't find DB. Path: " + DB());
        }
        return t;
    }

    protected boolean deleteFromDB(long id) throws InternalServerException, BadRequestException, FileNotFoundException {
        List<T> list = readObjectsFromFile();
        clearFile(DB());
        for (T t : list) {
            if (t.getId() != id) {
                writeToDB(t);
            }
        }
        return true;
    }

    protected List<T> readObjectsFromFile() throws BadRequestException, InternalServerException, FileNotFoundException {
        List<T> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB()))) {
            String line;
            while ((line = reader.readLine()) != null && !line.contentEquals("")) {
                list.add(objectFromString(line));
            }
        } catch (IOException e) {
            throw new InternalServerException("Can't read from DB: " + DB().getAbsolutePath());
        }
        return list;
    }

    public T findById(long id) throws BadRequestException, InternalServerException, FileNotFoundException {
        for (T t : readObjectsFromFile()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public abstract T objectFromString(String line) throws BadRequestException, InternalServerException, FileNotFoundException;

    private void clearFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract File DB() throws FileNotFoundException;


}
