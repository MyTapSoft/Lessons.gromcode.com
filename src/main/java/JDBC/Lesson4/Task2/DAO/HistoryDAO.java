package JDBC.Lesson4.Task2.DAO;

import JDBC.Lesson4.Task2.Exceptions.InternalServerException;
import JDBC.Lesson4.Task2.Model.History;
import JDBC.Lesson4.Task2.Model.OperationType;
import JDBC.Lesson4.Task2.Model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDAO {
    public static History saveToHistory(History history) throws SQLException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("INSERT INTO HISTORY VALUES(?, ? ,? ,?)")) {
            statement.setLong(1, history.getId());
            statement.setString(2, String.valueOf(history.getOperationType()));
            statement.setLong(3, history.getTimeProcessed());
            statement.setString(4, String.valueOf(history.getStatus()));

            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Transaction history hasn't been saved. ID: " + history.getId());

        }
        return history;
    }

    public static void dellHistory(long id) throws SQLException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("DELETE FROM HISTORY WHERE ID = ?")) {
            statement.setLong(1, id);
            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("Cant delete transaction history. ID: " + id);

        }
    }

    public static History getHistory(long id) throws SQLException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM HISTORY WHERE id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) throw new InternalServerException("Cant find history with ID: " + id);
            while (resultSet.next()) {
                History history = new History(OperationType.valueOf(resultSet.getString(2)), resultSet.getLong(3), Status.valueOf(resultSet.getString(4)));
                history.setId(resultSet.getLong(1));
                return history;
            }

        }
        return null;
    }

    public static History updateHistory(History history) throws SQLException, InternalServerException {
        try (Connection connection = JDBC.Lesson4.Connections.Connection.getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE HISTORY SET OPERATION_TYPE = ?, TIME_PROCESSED = ?, STATUS = ? WHERE ID = ?")) {
            statement.setString(1, String.valueOf(history.getOperationType()));
            statement.setLong(2, history.getTimeProcessed());
            statement.setString(3, String.valueOf(history.getStatus()));
            statement.setLong(4, history.getId());

            int res = statement.executeUpdate();
            if (res == 0) throw new InternalServerException("History hasn't been updated. ID: " + history.getId());

        }
        return history;
    }
}
