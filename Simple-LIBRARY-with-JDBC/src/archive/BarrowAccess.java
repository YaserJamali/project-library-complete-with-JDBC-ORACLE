//package lib.tamin.da;
//
//import lib.tamin.Barrow;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BarrowAccess {
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
//
//    }
//
//    public Barrow add(Barrow barrow) throws SQLException {
//        barrow.setBarrowId(nextId());
//        connection = ConnectionTools.connection();
//
//        statement = connection.prepareStatement("insert into BARROW (BARROW_ID,PERSON_ID,BOOK_NAME,RETURN_DATE) VALUES (?,?,?,?)");
//        statement.setInt(1, barrow.getBarrowId());
//        statement.setInt(2, barrow.getMemberId());
//        statement.setString(3, barrow.getBookName());
//        statement.setDate(4, barrow.getReturnDay());
//        statement.execute();
//
//        int result = statement.executeUpdate();
//        close();
//        if (result == 1) {
//            return barrow;
//        } else {
//            return null;
//        }
//
//    }//end of method barrow
//
//
//
//}//end of class
