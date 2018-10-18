package JDBC.Lesson4.Task2.Service;

import JDBC.Lesson4.Task2.DAO.FileDAO;
import JDBC.Lesson4.Task2.DAO.StorageDAO;
import JDBC.Lesson4.Task2.Exceptions.BadRequestException;
import JDBC.Lesson4.Task2.Model.File;
import JDBC.Lesson4.Task2.Model.Storage;

import java.sql.SQLException;

public class Service {
    public File put(Storage storage, File file) throws SQLException, BadRequestException {
        if (file.getStorage() == null) {
            file.setStorage(storage);
            if (FileDAO.getFile(file.getId()) == null) {
                FileDAO.saveFile(file);
            } else {
                FileDAO.updateFile(file);
            }
        } else if (file.getStorage().getId() == storage.getId())
            throw new BadRequestException("File with ID: " + file.getId() + " already belongs to storage with ID: " + storage.getId());
        else {
            transferFile(file.getStorage(), storage, file.getId());
        }
        return file;
    }

    public void delete(Storage storage, File file) throws BadRequestException, SQLException {
        if (file.getStorage() == null) {
            throw new BadRequestException("File with ID: " + file.getId() + " doesn't have storage");
        } else if (file.getStorage().getId() != storage.getId())
            throw new BadRequestException("File with ID: " + file.getId() + " already doesn't belongs to storage with ID: " + storage.getId());
        else {
            file.setStorage(null);
            if (FileDAO.getFile(file.getId()) == null) {
                FileDAO.saveFile(file);
            } else {
                FileDAO.updateFile(file);
            }
        }
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws SQLException, BadRequestException {
        File file = FileDAO.getFile(id);
        if (file == null) throw new BadRequestException("Fle doesn't exist");//Если файла не существует - вопрос почему? Либо ID не верный - это BadRequestException.
        // Либо он там должен быть, но его нет - тогда это InternalServerException
        if (file.getStorage().getId() != storageFrom.getId())
            throw new BadRequestException("File doesn't belong to store with ID: " + storageFrom.getId());
        file.setStorage(storageTo);
        FileDAO.updateFile(file);
        return file;
    }

    public void transferAll(Storage storageFrom, Storage storageTo) {
        StorageDAO.transferAll(storageFrom.getId(), storageTo.getId());
    }
}
