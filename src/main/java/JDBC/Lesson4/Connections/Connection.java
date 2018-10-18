package JDBC.Lesson4.Connections;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";
    private static String LOGIN = "root";
    private static String PASS = "260258aaa";

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, LOGIN, PASS);
    }
}
