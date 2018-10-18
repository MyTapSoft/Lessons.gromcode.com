package JDBC.Lesson4.Task2.Validator;



import JDBC.Lesson4.Task2.DAO.FileDAO;
import JDBC.Lesson4.Task2.DAO.StorageDAO;
import JDBC.Lesson4.Task2.Model.File;
import JDBC.Lesson4.Task2.Model.Storage;

import java.sql.SQLException;


public class Validator {
    public static void check(File file, Storage storage) throws SQLException {
        check(file);
        check(storage);
        if (FileDAO.allFilesSize(storage.getId()) + file.getFileSize() > storage.getStorageSize())
            throw new SQLException("Storage size limit. Storage ID: " + storage.getId() + ". File ID: " + file.getId());
        if (!compareFormats(file.getFormat(), storage.getFormatsSupported()))
            throw new SQLException("Storage ID: " + storage.getId() + " does not support the file format: " + file.getId());


    }

    public static void check(Storage storage) throws SQLException {
        if (storage == null) throw new NullPointerException("Storage is NULL!");
        if (storage.getFormatsSupported() == null && storage.getStorageCountry() == null)
            throw new NullPointerException("Some values in storage are NULL!");
        if (StorageDAO.getStorage(storage.getId()) == null)
            throw new SQLException("Storage with ID: " + storage.getId() + " doesn't exist");
    }

    public static void check(File file) {
        if (file == null) throw new NullPointerException("File is NULL!");
        if (file.getFormat() == null && file.getName() == null && file.getStorage() == null)
            throw new NullPointerException("Some values in file are NULL!");
    }

    public static void check(Storage storageFrom, Storage storageTo) throws SQLException {
        check(storageFrom);
        check(storageTo);
        if (FileDAO.allFilesSize(storageFrom.getId()) + FileDAO.allFilesSize(storageTo.getId()) > storageTo.getStorageSize())
            throw new SQLException("Storage size limit. Storage ID: " + storageTo.getId());
        if (!compareArrays(storageFrom.getFormatsSupported(), storageTo.getFormatsSupported()))
            throw new SQLException("Storages has different supported formats. ID: " + storageTo.getId() + ". ID: " + storageFrom.getId());

    }

    private static boolean compareArrays(String[] one, String[] two) {
        if (one.length != two.length) return false;
        for (int i = 0; i < one.length; i++) {
            if (!(one[i].equalsIgnoreCase(two[i]))) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareFormats(String fileFormat, String[] formats) {
        for (String var : formats) {
            if (var.equalsIgnoreCase(fileFormat)) return true;
        }
        return false;
    }


}
