//package menu;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import lib.tamin.da.ConnectionTools;
//
//public class Tese {
//    static PreparedStatement statement;
//    static Connection connection;
//
//    public static void close() throws SQLException {
//
//        statement.close();
//        connection.close();
//    }
//
//
//    public static void main(String[] args) throws SQLException {
//        Tese t = new Tese();
//        t.remove("Ali", "Karimi");
//
//    }
//
//    public void remove(String name, String family) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("DELETE FROM PERSON  WHERE NAME=? AND FAMILY=?");
//            statement.setString(1, name);
//            statement.setString(2, family);
//            statement.execute();
//            connection.close();
//        } catch (NullPointerException e) {
//            System.out.println("There Is No Member With This Name Or Family " + e.getMessage());
//        }
//    }
//
//
//}