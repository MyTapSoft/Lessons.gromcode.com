package JDBC.Lesson4.Task2.DAO;

import JDBC.Lesson4.Task2.Exceptions.BadRequestException;
import JDBC.Lesson4.Task2.Exceptions.InternalServerException;
import JDBC.Lesson4.Task2.Model.History;
import JDBC.Lesson4.Task2.Model.OperationType;
import JDBC.Lesson4.Task2.Model.Status;
import JDBC.Lesson4.Task2.Model.Storage;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

public class StorageDAO {

    public static Storage getStorage(long id) throws SQLException, BadRequestException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORAGE WHERE STORAGE_ID = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) throw new BadRequestException("There's no storage in DB with ID: " + id);
            while (resultSet.next()) {
                String[] formats = resultSet.getString(2).split(",");
                return new Storage(resultSet.getLong(1), formats, resultSet.getString(3), resultSet.getInt(4));
            }
        }
        return null;
    }

    public static Storage saveStorage(Storage storage) throws SQLException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO STORAGE VALUES(?, ?, ?, ?)")) {
            String extensions = Arrays.toString(storage.getFormatsSupported()).substring(1, Arrays.toString(storage.getFormatsSupported()).length() - 1).replaceAll("[\\s]{2,}", " ");
            statement.setLong(1, storage.getId());
            statement.setString(2, extensions);
            statement.setString(3, storage.getStorageCountry());
            statement.setInt(4, storage.getStorageSize());

            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Cant save Storage with ID: " + storage.getId());
        }
        return storage;
    }

    public static void deleteStorage(long id) throws SQLException, InternalServerException {
        Date startDate = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM STORAGE WHERE STORAGE_ID = ?")) {
            statement.setLong(1, id);
            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Cant delete Storage with ID: " + id);

        }
    }

    public static Storage updateStorage(Storage storage) throws SQLException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE STORAGE SET FORMATS_SUPPORTED = ?, STORAGE_COUNTRY = ?, STORAGE_SIZE = ? WHERE STORAGE_ID = ?")) {
            String extensions = Arrays.toString(storage.getFormatsSupported()).substring(1, Arrays.toString(storage.getFormatsSupported()).length() - 1).replaceAll("[\\s]{2,}", " ");
            statement.setString(1, extensions);
            statement.setString(2, storage.getStorageCountry());
            statement.setInt(3, storage.getStorageSize());
            statement.setLong(4, storage.getId());

            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Cant update Storage with ID: " + storage.getId());
        }
        return storage;
    }

    public static void transferAll(long storageFrom, long storageTo) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection()) {
            transferAllTransaction(connection, storageFrom, storageTo, startTime.getTime());
        } catch (SQLException e) {
            System.err.println("Can't transfer files from storage with ID: " + storageFrom + ". And storage with ID: " + storageTo);
            e.printStackTrace();
        }
    }


    private static void transferAllTransaction(Connection connection, long storageFrom, long storageTo, long startTime) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE STORAGE_ID = ?")) {
            connection.setAutoCommit(false);
            statement.setLong(1, storageTo);
            statement.setLong(2, storageFrom);
            int res = statement.executeUpdate();
            if (res == 0)
                throw new InternalServerException("Can't transfer files from storage with ID: " + storageFrom + " to storage with ID: " + storageTo);

            HistoryDAO.saveToHistory(new History(OperationType.TRANSFER_ALL_FILES, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException | InternalServerException e) {
            connection.rollback();
        }
    }
}
