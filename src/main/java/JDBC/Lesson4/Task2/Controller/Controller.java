package JDBC.Lesson4.Task2.Controller;

import JDBC.Lesson4.Task2.Exceptions.BadRequestException;
import JDBC.Lesson4.Task2.Exceptions.InternalServerException;
import JDBC.Lesson4.Task2.Model.*;
import JDBC.Lesson4.Task2.Service.Service;
import JDBC.Lesson4.Task2.Validator.Validator;

import java.sql.*;



public class Controller {
    private Service service = new Service();

    public File put(Storage storage, File file) throws SQLException, BadRequestException, InternalServerException {
        Validator.check(file, storage);
        return service.put(storage, file);
    }

    public void delete(Storage storage, File file) throws SQLException, BadRequestException, InternalServerException {
        Validator.check(file, storage);
        service.delete(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws SQLException, BadRequestException, InternalServerException {
        Validator.check(storageFrom, storageTo);
        service.transferAll(storageFrom, storageTo);
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws SQLException, BadRequestException, InternalServerException {
        Validator.check(storageFrom, storageTo);
        return service.transferFile(storageFrom, storageTo, id);
    }

}
