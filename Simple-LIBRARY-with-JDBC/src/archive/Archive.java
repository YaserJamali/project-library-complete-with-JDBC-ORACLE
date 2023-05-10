//package archive;
//
//import lib.tamin.BarrowManagement;
//import lib.tamin.Person;
//import lib.tamin.da.BookDataBase;
//import lib.tamin.da.ConnectionTools;
//import lib.tamin.da.PersonDataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.InputMismatchException;
//import java.util.List;
//
//public class Archive {
//    private Connection connection;
//    private PreparedStatement statement;
//
//    BookDataBase bA = new BookDataBase();
//    PersonDataBase pA = new PersonDataBase();
//    BarrowManagement barrowManagement = new BarrowManagement();
//
//
//
//
//
//    public void removeMemberByNameAndFamily(String name, String family) throws SQLException {
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
//    //    public boolean searchIdForBooks(int id) throws SQLException {
////        try {
////            connection = ConnectionTools.connection();
////            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID=? ORDER BY BOOK_NAME,WRITER,PUBLISHER");
////            statement.setInt(1, id);
////
////            ResultSet result = statement.executeQuery();
////
////            if (result.next() && id == result.getInt(1)) {
////                System.out.println("Book's ID Has Found");
////                return true;
////            } else {
////                System.out.println("There Is No Book ID With This ID: " + id);
////                return false;
////            }
////        } catch (InputMismatchException e) {
////            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
////                    + e.getMessage());
////
////        }
////        return false;
////    }
//
//    public void showTheBook() {
//        System.out.println("This Is The Book's Information");
//
//    }
//
//    public void removeTheBookWithBookNameOrWriter() {
//
//        System.out.println("book removed bay book name");
//
//    }
//    public void removePersonWithIdFromBarrowId(int id) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("DELETE FROM BARROW  WHERE PERSON_ID=?");
//            statement.setInt(1, id);
//            statement.execute();
//            connection.close();
//        } catch (NullPointerException e) {
//            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
//        }
//    }
//    //    public boolean searchIdBook(int id) throws SQLException {
////
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID=? ORDER BY ID");
////        statement.setInt(1, id);
////
////        ResultSet result = statement.executeQuery();
////
////        if (result.next() && id == result.getInt(1)) {
////            System.out.print("The Book's ID " + id + " Found The Book '" + result.getString("BOOK_NAME") + "' Who Wrote \"" + result.getString("WRITER") + "\"");
////            return true;
////        } else {
////            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
////            return false;
////        }
////    }
//
////    public void removePersonWithIdFromBarrowId(int id) throws SQLException {
////        try {
////            connection = ConnectionTools.connection();
////            statement = connection.prepareStatement("DELETE FROM BARROW  WHERE PERSON_ID=?");
////            statement.setInt(1, id);
////            statement.execute();
////            connection.close();
////        } catch (NullPointerException e) {
////            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
////        }
////    }
//
//    public void showBook() {
//        System.out.println("show the book");
//    }
//
//    public void addBook() {
//        System.out.println("Add Book With Person");
//
//    }
//    public void showTheMemberProfile() {
//        System.out.println("This Is a Person Identity");
//    }
//
//    public void returnTheBook() {
//        System.out.println("Member Returned The Book");
//
//    }
//    public boolean searchId(int id) throws SQLException {//undo from login method
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(1)) {
//            System.out.println("The ID Has Found");
//            return true;
//        } else {
//            System.out.println("\"ERROR\" The Id Has Not Found!!!");
//            return false;
//        }
//    }
//    public boolean searchIdBar(int id)  {
//        try {
//
//
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("SELECT * FROM BARROW,PERSON,BOOK WHERE BARROW_ID=? ORDER BY BARROW_ID");
//            statement.setInt(1, id);
//
//            ResultSet result = statement.executeQuery();
//
//            if (result.next() && id == result.getInt(1)) {
//                System.out.println("The Issue's ID " + id + " Found '" + result.getString("NAME") + " " + result.getString("FAMILY") + "' Issued Book \"" + result.getString("BOOK_NAME") + "\"");
//                return true;
//            } else {
//                System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
//                return false;
//            }
//        } catch (SQLException | NullPointerException | InputMismatchException e) {
//            System.out.println(e.getMessage());
//        }return false;
//    }
//    //    public boolean searchIdIssue(int id) throws SQLException {
////
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("SELECT * FROM BARROW WHERE BARROW_ID=? ORDER BY PERSON_ID,BOOK_NAME,RETURN_DATE");
////        statement.setInt(1, id);
////
////        ResultSet result = statement.executeQuery();
////
////        if (result.next() && id == result.getInt(1)) {
////            System.out.println("The ID Has Found");
////            return true;
////        } else {
////            System.out.println("\"ERROR\" The Id Has Not Found!!!");
////            return false;
////        }
////    }
////    public void removeBar(int id) throws SQLException {
////        try {
////            connection = ConnectionTools.connection();
////            statement = connection.prepareStatement("DELETE FROM BARROW  WHERE BARROW_ID=?");
////            statement.setInt(1, id);
////            statement.execute();
////            connection.close();
////        } catch (NullPointerException e) {
////            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
////        }
////    }
////
////    public void returnBook() {
////            try{
////        System.out.println("Please Enter Your Issue ID");
////        Scanner sc=new Scanner(System.in);
////        int issueId= sc.nextInt();
////        if (searchIdIssue(issueId)) {
////            removeBar(issueId);
////        }}catch (SQLException e){
////                System.out.println(e.getMessage());
////            }
////    }
////    public void eachMemberHasHowMuchBooksIssue(int id) {
////        try {
////
////            if (searchIdPerson(id)) {
////                findAllIssueBooks(id);
////            }
////        } catch (SQLException | NullPointerException e) {
////            System.out.println(e.getMessage());
////        }
////
////    }
////    public Barrow(String bookName) {
////        this.bookName = bookName;
////    }
////
//
////        Person perValue=new Person();
////        Scanner sc=new Scanner(System.in);
////perValue.setName(sc.next());
////perValue.setFamily(sc.next());
//////perValue.setRole();
////
////       Person person = new Person(perValue.getName(), perValue.getFamily(), Role.member, perValue.getPassword());
////        PersonAccess pA=new PersonAccess();
////    pA.add(person);
////        System.out.println(person+" Person Saved");
////      Person person=new Person("hasan","karimi",Role.employee);
////              pA.add(person);
////        System.out.println(person+"person added");
//
//    // System.out.println(" deleteed");
//    // pA.findByRole("member");
//    //   pA.findByNameAndFamily("ona","");
////        Book bookVal=new Book();
////        Book book=new Book("slave","fidor","Sabz");
////       BookAccess bA=new BookAccess();
////  bA.add(book);
////        System.out.println(book+"book added");
////        bA.findPassword(1);
//    //  System.out.println(pA.showAll());import lib.tamin.da.BookAccess;
//    //import lib.tamin.da.PersonAccess;import java.util.Scanner;
//    public List<Object> findAll() throws SQLException {
//        return null;
//    }
//
//    public void edit(Person person) throws SQLException {
//
//    }
//    //    public List findPassword(int id) throws SQLException {
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME");
////        statement.setInt(1, id);
////        ResultSet result = statement.executeQuery();
////        while (result.next()) {
////            System.out.println(result.getInt("ID"));
////            System.out.println(result.getString("NAME"));
////            System.out.println(result.getString("FAMILY"));
////            System.out.println(result.getString("PASSWORD"));
////        }
////        close();
////        return null;
////    }
//    //    public Book add(Book book) throws SQLException {
////        book.setId(nextId());
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("INSERT INTO BOOK (ID,BOOK_NAME,WRITER,PUBLISHER) VALUES (?,?,?,?)");
////        statement.setInt(1, book.getId());
////        statement.setString(2, book.getBookName());
////        statement.setString(3, book.getWriterName());
////        statement.setString(4, book.getPublisher());
////        int result = statement.executeUpdate();
////        close();
////        if (result == 1) {
////            return book;
////        } else {
////            return null;
////        }
////
////    }
//    //    public int nextId() throws SQLException {
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("select BOOK_SEQ.nextval as id from dual");
////        ResultSet result = statement.executeQuery();
////        result.next();
////        return result.getInt("id");
////
////    }
////    public Object add(Object object) throws SQLException {
////        return null;
////    }
//
//
//
//
//
//
//
//
//
//
//
//}
