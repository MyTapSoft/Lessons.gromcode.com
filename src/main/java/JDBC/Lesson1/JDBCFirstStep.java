package JDBC.Lesson1;

import java.sql.*;

public class JDBCFirstStep {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@test.cdl3cqobq0vs.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "root";
    private static final String PASS = "260258aaa";

    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Class " + JDBC_DRIVER + " not found");
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

            try (ResultSet rs = statement.executeQuery("SELECT * FROM TEST")) {
                while (rs.next()) {
                    System.out.println("Object found");

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
