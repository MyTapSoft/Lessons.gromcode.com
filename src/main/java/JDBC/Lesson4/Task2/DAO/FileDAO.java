package JDBC.Lesson4.Task2.DAO;


import JDBC.Lesson4.Task2.Model.File;
import JDBC.Lesson4.Task2.Model.History;
import JDBC.Lesson4.Task2.Model.OperationType;
import JDBC.Lesson4.Task2.Model.Status;

import java.sql.*;
import java.util.Date;


public class FileDAO {
    public static File saveFile(File file) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO FILES VALUES(?, ?, ?, ?, ?)")) {
            saveUpdate(connection, statement, file, startTime.getTime());

        } catch (SQLException e) {
            System.err.println("Can't save file with ID: " + file.getId());
            e.printStackTrace();
        }
        return file;
    }

    public static void deleteFile(long id) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM FILES WHERE ID = ?")) {
            dell(connection, statement, id, startTime.getTime());

        } catch (SQLException e) {
            System.err.println("Can't delete file with ID: " + id);
            e.printStackTrace();
        }
    }

    public static File getFile(long id) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM FILES WHERE ID = ?")) {
            File file = null;
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                file = new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), StorageDAO.getStorage(resultSet.getLong(5)));
            }
            if (file != null)
                HistoryDAO.saveToHistoryTransaction(new History(OperationType.GET_FILE, new Date().getTime() - startTime.getTime(), Status.SUCCESS));

        } catch (SQLException e) {
            System.err.println("Can't get file with ID: " + id);
            e.printStackTrace();
        }
        return null;
    }

    public static File updateFile(File file) {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET NAME = ?, FORMAT = ?, FILE_SIZE= ?, STORAGE_ID = ? WHERE ID = ?")) {
            saveUpdate(connection, statement, file, startTime.getTime());

        } catch (SQLException e) {
            System.err.println("Can't update File with id: " + file.getId());
            e.printStackTrace();
        }
        return file;
    }

    public static int allFilesSize(long storageId) throws SQLException {
        int totalSize = 0;
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT FILE_SIZE FROM FILES WHERE STORAGE_ID = ?")) {
            statement.setLong(1, storageId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalSize += resultSet.getInt(1);
            }
        }
        return totalSize;
    }

    private static void saveUpdate(Connection connection, PreparedStatement statement, File file, long startTime) throws SQLException {
        try {
            connection.setAutoCommit(false);
            statement.setLong(1, file.getId());
            statement.setString(2, file.getName());
            statement.setString(3, file.getFormat());
            statement.setInt(4, file.getFileSize());
            statement.setLong(5, file.getStorage().getId());

            statement.executeUpdate();
            HistoryDAO.saveToHistoryTransaction(new History(OperationType.ADD_UPDATE_FILE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    private static void dell(Connection connection, PreparedStatement statement, long fileId, long startTime) throws SQLException {
        try {
            connection.setAutoCommit(false);
            statement.setLong(1, fileId);
            statement.executeUpdate();
            HistoryDAO.saveToHistoryTransaction(new History(OperationType.DELETE_FILE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }
}
