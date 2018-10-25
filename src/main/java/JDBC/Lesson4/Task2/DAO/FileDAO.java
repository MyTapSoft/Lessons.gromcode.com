package JDBC.Lesson4.Task2.DAO;

import JDBC.Lesson4.Task2.Exceptions.BadRequestException;
import JDBC.Lesson4.Task2.Exceptions.InternalServerException;
import JDBC.Lesson4.Task2.Model.File;
import JDBC.Lesson4.Task2.Model.History;
import JDBC.Lesson4.Task2.Model.OperationType;
import JDBC.Lesson4.Task2.Model.Status;

import java.sql.*;
import java.util.Date;


public class FileDAO {
    public static File saveFile(File file) throws SQLException { //Я не могу здесь не использовать SQLException из за Connection connection = getConnection()
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection()) {
            saveTransaction(connection, file, startTime.getTime());
        }
        return file;
    }

    public static void deleteFile(long id) throws SQLException {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM FILES WHERE ID = ?")) {
            dellTransaction(connection, statement, id, startTime.getTime());

        }
    }

    public static File getFile(long id) throws SQLException, BadRequestException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM FILES WHERE ID = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) throw new InternalServerException("Can't findById file. ID: " + id);
            while (resultSet.next()) {
                return new File(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), StorageDAO.getStorage(resultSet.getLong(5)));
            }

        }
        return null;
    }

    public static File updateFile(File file) throws SQLException {
        Date startTime = new Date();
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection()) {
            updateTransaction(connection, file, startTime.getTime());

        }
        return file;
    }

    public static int allFilesSize(long storageId) throws SQLException, InternalServerException {
        int totalSize = 0;
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT FILE_SIZE FROM FILES WHERE STORAGE_ID = ?")) {
            statement.setLong(1, storageId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) throw new InternalServerException("Can't findById all files size. ID: " + storageId);

            while (resultSet.next()) {
                totalSize += resultSet.getInt(1);
            }
        }
        return totalSize;
    }

    private static void saveTransaction(Connection connection, File file, long startTime) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO FILES VALUES(?, ?, ?, ?, ?)")) {
            connection.setAutoCommit(false);
            statement.setLong(1, file.getId());
            statement.setString(2, file.getName());
            statement.setString(3, file.getFormat());
            statement.setInt(4, file.getFileSize());
            statement.setLong(5, file.getStorage().getId());

            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Can't save file. ID: " + file.getId()); //Я не знаю дает что-то эта проверка или нет?

            HistoryDAO.saveToHistory(new History(OperationType.ADD_UPDATE_FILE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException | InternalServerException e) {
            connection.rollback();
        }
    }

    private static void updateTransaction(Connection connection, File file, long startTime) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE FILES SET NAME = ?, FORMAT = ?, FILE_SIZE = ?, STORAGE_ID = ? WHERE ID = ?")) {
            connection.setAutoCommit(false);
            statement.setString(1, file.getName());
            statement.setString(2, file.getFormat());
            statement.setInt(3, file.getFileSize());
            statement.setLong(4, file.getStorage().getId());
            statement.setLong(5, file.getId());

            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Can't update file. ID: " + file.getId());

            HistoryDAO.saveToHistory(new History(OperationType.ADD_UPDATE_FILE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException | InternalServerException e) {
            connection.rollback();
        }
    }

    private static void dellTransaction(Connection connection, PreparedStatement statement, long fileId, long startTime) throws SQLException {
        try {
            connection.setAutoCommit(false);
            statement.setLong(1, fileId);
            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Can't delete file. ID: " + fileId);

            HistoryDAO.saveToHistory(new History(OperationType.DELETE_FILE, new Date().getTime() - startTime, Status.SUCCESS));
            connection.commit();
        } catch (SQLException | InternalServerException e) {
            connection.rollback();
        }
    }
}
