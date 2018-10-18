package JDBC.Lesson4.Task2.Controller;

import JDBC.Lesson4.Task2.DAO.FileDAO;
import JDBC.Lesson4.Task2.DAO.StorageDAO;
import JDBC.Lesson4.Task2.Model.File;
import JDBC.Lesson4.Task2.Model.Storage;
import JDBC.Lesson4.Task2.Validator.Validator;


import java.sql.*;



public class Controller {
    public File put(Storage storage, File file) throws SQLException {
        Validator.check(file, storage);

        if (file.getStorage() == null) {
            file.setStorage(storage);
            if (FileDAO.getFile(file.getId()) == null) {
                FileDAO.saveFile(file);
            } else {
                FileDAO.updateFile(file);
            }
        } else if (file.getStorage().getId() == storage.getId())
            throw new SQLException("File with ID: " + file.getId() + " already belongs to storage with ID: " + storage.getId());
        else {
            file.setStorage(storage);
            if (FileDAO.getFile(file.getId()) == null) {
                FileDAO.saveFile(file);
            } else {
                FileDAO.updateFile(file);
            }
        }

        return file;
    }

    public void delete(Storage storage, File file) throws SQLException {
        Validator.check(file, storage);
        if (file.getStorage() == null) {
            if (FileDAO.getFile(file.getId()) == null) {
                FileDAO.saveFile(file);
            } else {
                FileDAO.updateFile(file);
            }
        } else if (file.getStorage().getId() != storage.getId())
            throw new SQLException("File with ID: " + file.getId() + " already doesn't belongs to storage with ID: " + storage.getId());
        else {
            file.setStorage(null);
            if (FileDAO.getFile(file.getId()) == null) {
                FileDAO.saveFile(file);
            } else {
                FileDAO.updateFile(file);
            }
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws SQLException {
        Validator.check(storageFrom, storageTo);
        StorageDAO.transferAll(storageFrom.getId(), storageTo.getId());
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws SQLException {
        Validator.check(storageFrom, storageTo);
        File file = FileDAO.getFile(id);
        if (file == null) throw new SQLException("Fle doesn't exist");

        if (file.getStorage().getId() != storageFrom.getId())
            throw new SQLException("File doesn't belong to store with ID: " + storageFrom.getId());
        file.setStorage(storageTo);
        FileDAO.updateFile(file);

        return file;

    }

}
