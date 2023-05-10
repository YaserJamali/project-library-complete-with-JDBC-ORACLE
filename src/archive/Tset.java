//package lib.tamin.da;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Tset {
//    private Connection connection;
//    private PreparedStatement statement;
//
//    public void close() throws SQLException {
//        statement.close();
//        connection.close();
//    }
//
//    public int nextId() throws SQLException {
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("select BOOK_SEQ.nextval as id from dual");
//        ResultSet result = statement.executeQuery();
//        result.next();
//        return result.getInt("id");
//    }
//
//    public static void main(String[] args) throws SQLException {
//        Connection connection;
//        PreparedStatement statement;
//
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT BOOK_NAME,WRITER,PUBLISHER from BOOK WHERE ID=?");
//        statement.setInt(1, 2001);
//        ResultSet result = statement.executeQuery();
//        List list = new ArrayList<>();
//        while (result.next()) {
//            list.add(result.getString("BOOK_NAME"));
//            list.add(result.getString("WRITER"));
//            list.add(result.getString("PUBLISHER"));
//            // System.out.println(result.getString("WRITER"));
//            //    System.out.println(result.getString("PUBLISHER"));
//
//        }
//        System.out.println(list);
//    }
//
//}
