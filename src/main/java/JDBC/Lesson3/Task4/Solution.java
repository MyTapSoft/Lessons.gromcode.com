package JDBC.Lesson3.Task4;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solution {
    private List<Long> freeIds = new ArrayList<>();
    private List<Long> lastAddedId = new ArrayList<>();

    public long testSavePerformance() throws SQLException {
        freeIds.clear();
        lastAddedId.clear();
        populateArray(freeIds);
        freeIds.removeAll(getAllIdFromDB());
        Date before = new Date();
        for (int i = 0; i < 20; i++) {
            TEST_SPEED test_speed = new TEST_SPEED(freeIds.get(i), String.valueOf(i), i);
            save(test_speed);
            lastAddedId.add(freeIds.get(i));
        }
        return (new Date().getTime() - before.getTime()) / 1000;
    }

    public long testDeleteByIdPerformance() {
        int index = 0;
        int count;
        Date before = new Date();
        if (lastAddedId.size() > 0) {
            count = lastAddedId.size();
            while (count-- > 0) {
                delete(lastAddedId.get(index++));
            }
        } else {
            count = 20;
            while (count-- > 0)
            delete(index++);
        }

        return (new Date().getTime() - before.getTime()) / 1000;

    }

    public long testDeletePerformance() {
        Date before = new Date();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            int res = statement.executeUpdate("DELETE FROM TEST_SPEED WHERE ROWNUM <= 10");
            if (res == 0) throw new SQLException("Nothing has deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (new Date().getTime() - before.getTime()) / 1000;
    }

    public long testSelectByIdPerformance() {
        int index = 0;
        int count;
        Date before = new Date();
        if (lastAddedId.size() > 0) {
            count = lastAddedId.size();
            while (count-- > 0) {
               get(lastAddedId.get(index++));
            }
        } else {
            count = 20;
            while (count-- > 0)
                get(index++);
        }
        return (new Date().getTime() - before.getTime()) / 1000;
    }

    public long testSelectPerformance() {
        Date before = new Date();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST_SPEED WHERE ROWNUM <= 10");
            if (resultSet == null) throw new SQLException("Nothing has founded");
            while (resultSet.next()) {
                TEST_SPEED test = new TEST_SPEED(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (new Date().getTime() - before.getTime()) / 1000;
    }

    private TEST_SPEED save(TEST_SPEED test_speed) throws SQLException {
        if (test_speed.getSOME_STRING() == null) throw new SQLException("String is null");

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO TEST_SPEED VALUES(?, ?, ?)")) {
            preparedStatement.setLong(1, test_speed.getID());
            preparedStatement.setString(2, test_speed.getSOME_STRING());
            preparedStatement.setInt(3, test_speed.getSOME_NUMBER());

            int res = preparedStatement.executeUpdate();
            if (res == 0) throw new SQLException("Nothing has saved");


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test_speed;
    }

    private void delete(long id) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM TEST_SPEED WHERE ID = ?")) {
            preparedStatement.setLong(1, id);

            int res = preparedStatement.executeUpdate();
            if (res == 0) throw new SQLException("Nothing has deleted");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TEST_SPEED get(long id) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TEST_SPEED WHERE ID = ?")) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) throw new SQLException("Nothing has deleted");
            while (resultSet.next()) {
                return new TEST_SPEED(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
        String USER = "root";
        String PASS = "260258aaa";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private ArrayList<Long> getAllIdFromDB() {
        ArrayList<Long> existIds = new ArrayList<>();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT ID FROM TEST_SPEED")) {
                while (resultSet.next()) {
                    existIds.add(resultSet.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existIds;
    }

    private void populateArray(List<Long> array) {
        for (int i = 0; i < 1000; i++) {
            array.add((long) i);
        }
    }

}
