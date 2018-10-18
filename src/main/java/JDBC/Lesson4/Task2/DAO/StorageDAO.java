package JDBC.Lesson4.Task2.DAO;

import JDBC.Lesson4.Task2.Model.History;
import JDBC.Lesson4.Task2.Model.OperationType;
import JDBC.Lesson4.Task2.Model.Status;
import JDBC.Lesson4.Task2.Model.Storage;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;

public class StorageDAO {

    public static Storage getStorage(long id) {
        Date startTime = new Date();
        Storage storage = null;
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM STORAGE WHERE STORAGE_ID = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) throw new SQLException("There's no storage in DB with ID: " + id);
            while (resultSet.next()) {
                String[] formats = resultSet.getString(2).split(",");
                storage = new Storage(resultSet.getLong(1), formats, resultSet.getString(3), resultSet.getInt(4));
            }
            if (storage != null) {
                HistoryDAO.saveToHistoryTransaction(new History(OperationType.GET_STORAGE, new Date().getTime() - startTime.getTime(), Status.SUCCESS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storage;
    }

    public static Storage saveStorage(Storage storage) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO STORAGE VALUES(?, ?, ?, ?)")) {
            saveUpdateTransaction(connection, statement, storage, startTime.getTime());
        } catch (SQLException e) {
            System.err.println("Can't save storage with ID: " + storage.getId());
            e.printStackTrace();
        }
        return storage;
    }

    public static void deleteStorage(long id) {
        Date startDate = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM STORAGE WHERE STORAGE_ID = ?")) {
            dellTransaction(connection, statement, id, startDate.getTime());

        } catch (SQLException e) {
            System.err.println("Can't delete storage with ID: " + id);
            e.printStackTrace();
        }
    }

    public static Storage updateStorage(Storage storage) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE STORAGE SET FORMATS_SUPPORTED = ?, STORAGE_COUNTRY = ?, STORAGE_SIZE = ? WHERE STORAGE_ID = ?")) {
            saveUpdateTransaction(connection, statement, storage, startTime.getTime());

        } catch (SQLException e) {
            System.err.println("Can't delete storage with ID: " + storage.getId());
            e.printStackTrace();
        }
        return storage;
    }

    public static void transferAll(long storageFrom, long storageTo) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE STORAGE_ID = ?")) {
            transferAllTransaction(connection, statement, storageFrom, storageTo, startTime.getTime());
        } catch (SQLException e) {
            System.err.println("Can't transfer files from storage with ID: " + storageFrom + ". And storage with ID: " + storageTo);
            e.printStackTrace();
        }
    }

    private static void saveUpdateTransaction(Connection connection, PreparedStatement statement, Storage storage, long startTime) throws SQLException {
        try {
            connection.setAutoCommit(false);
            String extensions = Arrays.toString(storage.getFormatsSupported()).substring(1, Arrays.toString(storage.getFormatsSupported()).length() - 1).replaceAll("[\\s]{2,}", " ");
            statement.setLong(1, storage.getId());
            statement.setString(2, extensions);
            statement.setString(3, storage.getStorageCountry());
            statement.setInt(4, storage.getStorageSize());

            statement.executeUpdate();
            HistoryDAO.saveToHistoryTransaction(new History(OperationType.ADD_UPDATE_STORAGE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private static void dellTransaction(Connection connection, PreparedStatement statement, long storageId, long startTime) throws SQLException {
        try {
            connection.setAutoCommit(false);
            statement.setLong(1, storageId);
            statement.executeUpdate();
            HistoryDAO.saveToHistoryTransaction(new History(OperationType.DELL_STORAGE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private static void transferAllTransaction(Connection connection, PreparedStatement statement, long storageFrom, long storageTo, long startTime) throws SQLException {
        try {
            connection.setAutoCommit(false);
            statement.setLong(1, storageTo);
            statement.setLong(2, storageFrom);
            int res = statement.executeUpdate();
            HistoryDAO.saveToHistoryTransaction(new History(OperationType.TRANSFER_ALL_FILES, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
