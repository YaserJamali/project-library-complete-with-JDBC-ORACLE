package lib.tamin.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionTools {

    static PreparedStatement statement;
    static Connection connection;

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ali", "123");

    }

    public static void close() throws SQLException {

        statement.close();
        connection.close();
    }

}
