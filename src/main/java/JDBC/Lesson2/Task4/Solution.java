package JDBC.Lesson2.Task4;

import java.sql.*;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "root";
    private static final String PASS = "260258aaa";


    public int increasePrice() throws Exception {
        try (Statement statement = getConnection().createStatement()) {
            int result = statement.executeUpdate("UPDATE PRODUCT SET PRICE = PRICE + 100 WHERE (PRICE < 970) ");
            if (result == 0) throw new Exception("Nothing has change");

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new Exception("Something wrong");
    }

    public int changeDescription() throws Exception {
        try (Statement statement = getConnection().createStatement()) {
            int result = statement.executeUpdate("UPDATE PRODUCT SET DESCRIPTION = SUBSTR(DESCRIPTION, INSTR(DESCRIPTION, '.', -1)-1) WHERE LENGTH(DESCRIPTION) > 100");
            if (result == 0) throw new Exception("Nothing has change");
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}