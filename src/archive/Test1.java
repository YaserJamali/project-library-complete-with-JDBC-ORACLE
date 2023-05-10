//package menu;
//
//import lib.tamin.Person;
//
//import java.sql.SQLException;
//
//
//import lib.tamin.da.ConnectionTools;
//
//import java.sql.*;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Test {
//
//    private Connection connection;
//    private PreparedStatement statement;
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
//    public Person add(Person person) throws SQLException {
//        person.setId(nextId());
//        connection = ConnectionTools.connection();
//
//        statement = connection.prepareStatement("insert into PERSON (ID,NAME,FAMILY,PASSWORD) VALUES (?,?,?,?)");
//        statement.setInt(1, person.getId());
//        statement.setString(2, person.getName());
//        statement.setString(3, person.getFamily());
//
//        statement.setString(4, person.getPassword());
//        int result = statement.executeUpdate();
//        statement.close();
//        connection.close();
//        if (result == 1) {
//            return person;
//        } else {
//            return null;
//        }
//
//    }
//
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
//        statement.close();
//        connection.close();
//
//        return list;
//    }
//
//    public String findByID(int id) throws SQLException {
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
//        statement.setInt(1, id);
//        ResultSet result = statement.executeQuery();
//        result.next();
//
//        return result.getString("password");
//    }
//
//    public void compare() throws SQLException {
//        while (true) {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("plz enter your id");
//            int id = sc.nextInt();
//            if (searchId(id)) {
//                System.out.println("plz enter your password");
//                String pass = sc.next();
//                if (pass.equals(findByID(id))) {
//                    System.out.println("your logged in");
//                } else System.out.println("PASSWORD IS WRONG");
//            } else return;
//
//        }
//    }
//
//    public boolean searchId(int id) throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(1)) {
//            System.out.println("you have ID");
//            return true;
//        } else {
//            System.out.println("this id have not found");
//            return false;
//        }
//
//
//    }
//    public List findAllIssueBooks(int personId) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("SELECT PERSON.ID,NAME,FAMILY,BOOK.BOOK_NAME,WRITER,PUBLISHER,RETURN_DATE,BARROW_ID FROM PERSON,BOOK,BARROW WHERE PERSON_ID=?  and PERSON.ID=BArrow.PERSON_ID and BOOK.BOOK_NAME=BARROW.BOOK_NAME ORDER BY PERSON_ID");
//            statement.setInt(1, personId);
//            ResultSet result = statement.executeQuery();
//            List list = new ArrayList<>();
//            int count = 0;
//            // System.out.println(result.getString("NAME")+" Gave "+count+" From Tamin Library");
//            while (result.next()) {
//
//                list.add(result.getInt("ID"));
//                list.add(result.getString("NAME"));
//                list.add(result.getString("FAMILY"));
//                list.add(result.getString("BOOK_NAME"));
//                list.add(result.getString("WRITER"));
//                list.add(result.getString("PUBLISHER"));
//                list.add(result.getInt("BARROW_ID"));
//                list.add("\n");
//                count++;
//            }
//            System.out.println("'" + list.get(1) + " " + list.get(2) + "' Gave '" + count + "' Books From Tamin Library");
//            System.out.println(list);
//            statement.close();
//            connection.close();
//            if (list.size() != 0)
//                return list;
//            else System.out.println("There Is No Match For Your Search");
//            return null;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println(e.getMessage() + "'This Id Is Not Exist Any More'");
//        }
//        return null;
//    }
//
//}
