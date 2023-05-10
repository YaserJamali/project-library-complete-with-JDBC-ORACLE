//package lib.tamin;
//
//import lib.tamin.da.ConnectionTools;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//
//public class Test12 {
//    private Connection connection;
//    private PreparedStatement statement;
//
//    public void close() throws SQLException {
//        statement.close();
//        connection.close();
//    }
//
//
//
//
//
//
//
//    public static void main(String[] args) throws IOException, SQLException {
//        File file=new File("test.txt");
//        OutputStream oS= Files.newOutputStream(file.toPath());
//        List list=new ArrayList();
//        Test12 test=new Test12();
//
//        list.add(test.showAll());
//        oS.write(String.valueOf(list).getBytes());
//        oS.flush();
//        oS.close();
//    }
//    public List<Person> showAll() throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM PERSON  ORDER BY FAMILY,NAME");
//        ResultSet result = statement.executeQuery();
//        List<Person> list = new ArrayList<>();
//        while (result.next()) {
//            Person person = new Person(result.getInt("id"), result.getString("name"), result.getString("family"),
//                    result.getString("password"));
//            list.add(person);
//        }
//        close();
//        return list;
//    }
//
//}
