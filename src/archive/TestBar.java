//package lib.tamin.da;
//
//import lib.tamin.Barrow;
//import lib.tamin.*;
//import menu.Test;
//
//import java.sql.*;
//
//public class TestBar {
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
//        statement = connection.prepareStatement("select BARROW_SEQ.nextval as id from dual");
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
//        int result = statement.executeUpdate();
//        close();
//        if (result == 1) {
//            return barrow;
//        } else {
//            return null;
//        }
//
//    }
//
//
//    public static void main(String[] args) throws SQLException {
//        Barrow barrow = new Barrow();
//        barrow.setMemberId(1001);
//        barrow.setBookName("the old");
//        Date date = new Date(2022, 6, 7);
//        barrow.setReturnDay(date);
//        Barrow b = new Barrow(barrow.getMemberId(), barrow.getBookName(), barrow.getReturnDay());
//        BarrowAccess bAc = new BarrowAccess();
//        TestBar testBar = new TestBar();
//        testBar.add(b);
//        System.out.println(b);
//
//
//    }
//
//
//}//end of class
//
//
//
//
//
