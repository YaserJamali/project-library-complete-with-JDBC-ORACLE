//package archive;
//
//import lib.tamin.Barrow;
//import lib.tamin.BarrowManagement;
//import lib.tamin.Book;
//import lib.tamin.da.BookDataBase;
//import lib.tamin.da.ConnectionTools;
//import lib.tamin.da.PersonDataBase;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Scanner;
//
//
//public class Test {
//
//    private Connection connection;
//    private PreparedStatement statement;
//    Barrow barrow = new Barrow();
//
//    public void close() throws SQLException {
//        statement.close();
//        connection.close();
//    }
//
////    public void issueRegistration() throws SQLException {
////        try {
////            Scanner sc = new Scanner(System.in);
////            Barrow barrowEmpty = new Barrow();
////            System.out.print("Please Enter Your Membership id:");
////            int id = sc.nextInt();
////            System.out.print("Please Enter The Demand Book Name:");
////            Scanner sc1 = new Scanner(System.in);
////            String bookName = sc1.nextLine();
////            if (searchId(id))
////                if (searchName(bookName)) {
////                    barrowEmpty.setMemberId(id);
////                    barrowEmpty.setBookName(bookName);
////                    System.out.print("Please The Year For Return:");
////                    int year = sc.nextInt();
////                    System.out.print("Please The Month For Return:");
////                    int month = sc.nextInt();
////                    System.out.print("Please The Day For Return:");
////                    int day = sc.nextInt();
////                    Date date = new Date(year, month, day);
////                    barrowEmpty.setReturnDay(date);
////
////
////                    Barrow barrow = new Barrow(barrowEmpty.getMemberId(), barrowEmpty.getBookName(), barrowEmpty.getReturnDay());
////                    add(barrow);
////                    System.out.println("You Issued The Book With This" +
////                            " Information:" + barrow);
////                }
//////            System.out.println("Member ID Or Book Name Was Not Found");
////        } catch (NullPointerException e) {
////            System.out.println(e.getMessage());
////        }
////
////    }
////
//
////
////
////    public boolean searchName(String name) throws SQLException {
////        try {
////            connection = ConnectionTools.connection();
////            statement = connection.prepareStatement("SELECT BOOK_NAME,WRITER,PUBLISHER FROM BOOK WHERE BOOK_NAME like ? ORDER BY BOOK_NAME,WRITER,PUBLISHER");
////            statement.setString(1, "%" + name + "%");
////
////            ResultSet result = statement.executeQuery();
////            if (result.next() && result.getString(1).contains(name)) {
////                System.out.println("The Book Name Found Has Found:\"" + result.getString(1) + "\"");
////                return true;
////            } else {
////                System.out.println("There Is No Book Name With This Book NAme You Searched:" + name);
////                return false;
////            }
////        } catch (SQLException | NullPointerException e) {
////            System.out.println(e.getMessage());
////
////        } catch (InputMismatchException e) {
////            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
////                    + e.getMessage());
////        }
////        return false;
////    }
////
////    public int nextId() throws SQLException {
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("select BARROW_SEQ.nextval as id from dual");
////        ResultSet result = statement.executeQuery();
////        result.next();
////        return result.getInt("id");
////
////    }
////
////    public Barrow add(Barrow barrow) throws SQLException {
////        barrow.setBarrowId(nextId());
////        connection = ConnectionTools.connection();
////
////        statement = connection.prepareStatement("insert into BARROW (BARROW_ID,PERSON_ID,BOOK_NAME,RETURN_DATE) VALUES (?,?,?,?)");
////        statement.setInt(1, barrow.getBarrowId());
////        statement.setInt(2, barrow.getMemberId());
////        statement.setString(3, barrow.getBookName());
////        statement.setDate(4, barrow.getReturnDay());
////        int result = statement.executeUpdate();
////        close();
////        if (result == 1) {
////            return barrow;
////        } else {
////            return null;
////        }
////
////    }
////
////    public String findPassword(int id) throws SQLException {
////        Connection connection;
////        PreparedStatement statement;
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
////        statement.setInt(1, id);
////        ResultSet result = statement.executeQuery();
////        result.next();
////
////        return result.getString("password");
////    }
////
////
////
////    public boolean login() throws SQLException, InputMismatchException {
////        try {
////            PersonAccess pA=new PersonAccess();
////            while (true) {
////                Scanner sc = new Scanner(System.in);
////                System.out.print("Please Enter Your Membership ID:");
////                int id = sc.nextInt();
////                if (searchId(id)) {
////                    System.out.print("Please Enter Your Password:");
////                    Scanner sc1=new Scanner(System.in);
////                    String pass = sc1.next();
////                    if (pass.equals(findPassword(id))) {
////                        System.out.println("You Logged In Successfully");
////                        System.out.println("Profile: " + pA.findByID(id));
////                        return true;
////                    } else System.out.println("PASSWORD IS WRONG:Please Try Again");
////                    break;
////                }
////                break;
////
////            }
////            return false;
////        } catch (InputMismatchException e) {
////            System.out.println(e.getMessage());
////            System.out.println("\t\t\t(\"InputMismatchException\" You Have To Use Digit Numbers ONLY)");
////
////        }
////        return false;
////    }
////public void removeBar(int id) throws SQLException {
////    try {
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("DELETE FROM BARROW  WHERE BARROW_ID=?");
////        statement.setInt(1, id);
////        statement.execute();
////        connection.close();
////    } catch (NullPointerException e) {
////        System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
////    }
////}
////    public boolean searchIdIssueBook(int id) throws SQLException {
////
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("SELECT * FROM BARROW WHERE BARROW_ID=? ORDER BY BOOK_NAME,PERSON_ID,RETURN_DATE");
////        statement.setInt(1, id);
////
////        ResultSet result = statement.executeQuery();
////
////        if (result.next() && id == result.getInt(2)) {
////            System.out.println("The ID Has Found");
////            return true;
////        } else {
////            System.out.println("\"ERROR\" The Id Has Not Found!!!");
////            return false;
////        }
////    }
////
////    public void returnBook() throws SQLException {
////    System.out.println("Please Enter Your Issue ID");
////    Scanner sc=new Scanner(System.in);
////    int issueId= sc.nextInt();
////    if (searchIdIssueBook(issueId)) {
////        removeBar(issueId);
////
////    }
////}
//
////    public boolean searchIdPerson(int id) throws SQLException {
////
////        connection = ConnectionTools.connection();
////        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
////        statement.setInt(1, id);
////
////        ResultSet result = statement.executeQuery();
////
////        if (result.next() && id == result.getInt(1)) {
////            System.out.println("The ID " + id + " Found By This Identity \"" + result.getString("NAME") + " " + result.getString("FAMILY") + "\"");
////            return true;
////        } else {
////            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
////            return false;
////        }
////    }
//
//    public boolean searchIdBook(int id) throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID=? ORDER BY ID");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(1)) {
//            System.out.println("The Book's ID " + id + " Found The Book '" + result.getString("BOOK_NAME") + "' Who Wrote \"" + result.getString("WRITER") + "\"");
//            return true;
//        } else {
//            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
//            return false;
//        }
//    }
//
//    public boolean searchIdBar(int id) throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM BARROW,PERSON,BOOK WHERE BARROW_ID=? ORDER BY BARROW_ID");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(1)) {
//            System.out.println("The Issue's ID " + id + " Found '" + result.getString("NAME") + " " + result.getString("FAMILY") + "' Issued Book \"" + result.getString("BOOK_NAME") + "\"");
//            return true;
//        } else {
//            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
//            return false;
//        }
//    }
//
//    public boolean searchPersonIdFromBarrow(int id) throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT PERSON_ID FROM BARROW WHERE PERSON_ID=? ");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(1)) {
//            System.out.println("The Person's ID " + id + " Found In Issues Book ");
//            return true;
//
//        } else {
//            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
//            return false;
//        }
//    }
//
//
//    public void editBook() {
//        try {
//            Scanner sc = new Scanner(System.in);
//            Book bookEmpty = new Book();
//            System.out.print("Please Enter The Book's ID:");
//            int id = sc.nextInt();
//            if (searchIdBook(id)) {
//                Scanner sc1 = new Scanner(System.in);
//                bookEmpty.setId(id);
//                System.out.print("What IS The New Name For This Book:");
//                String bookNAme = "" + sc1.nextLine();
//                bookEmpty.setBookName(bookNAme);
//                System.out.print("What IS New Writer Name:");
//                bookEmpty.setWriterName(sc1.nextLine());
//                System.out.print("What IS New Publisher Name:");
//                bookEmpty.setPublisher(sc1.nextLine());
//
//                Book book = new Book(bookEmpty.getId(), bookEmpty.getBookName(),
//                        bookEmpty.getWriterName(), bookEmpty.getPublisher());
//                BookDataBase bA = new BookDataBase();
//                bA.edit(book);
//                System.out.println(book);
//            }
//        } catch (SQLException e) {
//            System.out.println("\"ERROR\" Please Use 20 Characters For Each Field!!!: "
//                    + e.getMessage());
//        } catch (InputMismatchException e) {
//            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
//                    + e.getMessage());
//
//        }
//    }
//
//    public void editBarrowByBookName(Barrow barrow) throws SQLException {
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("UPDATE BOOK SET BOOK_NAME=? WHERE ID=?");
//        statement.setString(1, barrow.getBookName());
//        statement.setString(2, barrow.getBookName());
//
//        statement.execute();
//        close();
//    }
//
//
//    public void remove(int id) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("DELETE FROM BARROW  WHERE BARROW_ID=?");
//            statement.setInt(1, id);
//            statement.execute();
//            connection.close();
//        } catch (NullPointerException e) {
//            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
//        }
//    }
//
//    public void returnBook(int idPerson) throws SQLException {
//        //  System.out.print("Please Enter Your Issue's Book ID:");
//        // Scanner sc = new Scanner(System.in);
//        //  int issueId = sc.nextInt();
//        //   if (searchIdBar(issueId)) {
//        //   remove(issueId);
//        //    System.out.println("You Returned The Book With This ID \"" + issueId + "\" Successfully");
//
//    }
//
//
//    public void edit(Barrow barrow1) throws SQLException {
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("UPDATE BARROW SET BOOK_NAME=? WHERE BOOK_NAME=?");
//        statement.setString(1, barrow1.getBookName());
//        statement.execute();
//        close();
//    }
//    public boolean searchIdIssueBook(int id) throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM BARROW WHERE BARROW_ID=? ORDER BY BOOK_NAME,PERSON_ID,RETURN_DATE");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(2)) {
//            System.out.println("The ID Has Found");
//            return true;
//        } else {
//            System.out.println("\"ERROR\" The Id Has Not Found!!!");
//            return false;
//        }
//    }
//
//
//
//    public boolean searchIdPerson(int id) throws SQLException {
//
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
//        statement.setInt(1, id);
//
//        ResultSet result = statement.executeQuery();
//
//        if (result.next() && id == result.getInt(1)) {
//            System.out.println("The ID " + id + " Found By This Identity \"" + result.getString("NAME") + " " + result.getString("FAMILY") + "\"");
//            return true;
//        } else {
//            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
//            return false;
//        }
//    }
//
//
//
//
//    public void removeTheMemberById() {
//        try {
//
//            Scanner sc = new Scanner(System.in);
//            System.out.print("Please Enter Member ID:");
//            int id = sc.nextInt();
//            if (searchIdPerson(id)) {
//                remove(id);
//                System.out.println("Member: " + id + " Removed");
//                if (searchPersonIdFromBarrow(id))
//                    removePersonWithIdFromBarrowId(id);
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (InputMismatchException e) {
//            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
//                    + e.getMessage());
//        }
//    }
//
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
//
//
//
//
//
//    public void removeBookWithBookName() {
//        try {
//            System.out.print("Enter The Book Name:");
//            Scanner sc = new Scanner(System.in);
//            String bookName = sc.nextLine();
//            if (searchBookNameExactly(bookName)) {
//                {
//                    removeBookWithBookName(bookName);
//                    System.out.println("The Book '" + bookName + "' Removed Successfully From Tamin Library");
//                }
//                if (searchBookNameExactlyFromBarrow(bookName)) {
//                    removeBookWithBookNameFromBarrow(bookName);
//                    System.out.println("The Book '" + bookName + "' Removed Successfully From Issued Books");
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//
//    public boolean searchBookNameExactlyFromBarrow(String name) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("SELECT * FROM BARROW WHERE BOOK_NAME=?");
//            statement.setString(1, name);
//
//            ResultSet result = statement.executeQuery();
//            if (result.next() && name.equals(result.getString("BOOK_NAME"))) {
//                System.out.println("The Book Name Found Has Found:\"" + name + "\" In Issues Book");
//                return true;
//            } else {
//                System.out.println("There Is No Book Name With This Book Name You Searched:" + name + " In Issues Book");
//                return false;
//            }
//        } catch (SQLException | NullPointerException e) {
//            System.out.println(e.getMessage());
//
//        } catch (InputMismatchException e) {
//            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
//                    + e.getMessage());
//        }
//        return false;
//    }
//    public boolean searchBookNameExactly(String name) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE BOOK_NAME=?");
//            statement.setString(1, name);
//
//            ResultSet result = statement.executeQuery();
//            if (result.next() && name.equals(result.getString("BOOK_NAME"))) {
//                System.out.println("The Book Name Found Has Found:\"" + name + "\"");
//                return true;
//            } else {
//                System.out.println("There Is No Book Name With This Book Name You Searched:" + name);
//                return false;
//            }
//        } catch (SQLException | NullPointerException e) {
//            System.out.println(e.getMessage());
//
//        } catch (InputMismatchException e) {
//            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
//                    + e.getMessage());
//        }
//        return false;
//    }
//    public void removeBookWithBookNameFromBarrow(String bookName) throws SQLException {
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("DELETE FROM BARROW WHERE BOOK_NAME=?");
//        statement.setString(1, bookName);
//        statement.execute();
//        close();
//    }
//    public void removeBookWithBookName(String bookName) throws SQLException {
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("DELETE FROM BOOK WHERE BOOK_NAME=?");
//        statement.setString(1, bookName);
//        statement.execute();
//        close();
//    }
//
//
//
//
//
//
//    public List findAllIssueBooks(int personId) throws SQLException {
//        try {
//            connection = ConnectionTools.connection();
//            statement = connection.prepareStatement("SELECT PERSON.ID,NAME,FAMILY,BOOK.BOOK_NAME,WRITER,PUBLISHER,RETURN_DATE,BARROW_ID FROM PERSON,BOOK,BARROW WHERE PERSON_ID=?  and PERSON.ID=PERSON_ID and BOOK.BOOK_NAME=BARROW.BOOK_NAME ORDER BY PERSON_ID");
//            statement.setInt(1, personId);
//            ResultSet result = statement.executeQuery();
//            List list = new ArrayList<>();
//            int count = 0;
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
//            System.out.println(e.getMessage() + "'This Id Is Not Exist Any More Or This Book Not Exist In Tamin library'");
//        }
//        return null;
//    }
//    public List findByID(int id) throws SQLException {
//        try{
//        connection = ConnectionTools.connection();
//        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME");
//        statement.setInt(1, id);
//        ResultSet result = statement.executeQuery();
//        List list = new ArrayList<>();
//        while (result.next()) {
//            list.add("UserName:"+result.getInt("ID"));
//            list.add("Name:"+result.getString("NAME"));
//            list.add("Family:"+result.getString("FAMILY"));
//            list.add("Password:"+result.getString("PASSWORD"));
//        }
//        statement.close();
//        connection.close();
//        return list;}catch (SQLException|InputMismatchException|NullPointerException e){
//            System.out.println(e.getMessage());
//        }return null;
//    }
//
//    public void findTheMemberWithIdentity() {//cheddddddd***********
//        try {
//            PersonDataBase pA=new PersonDataBase();
//            System.out.print("Please Enter The Name:");
//            Scanner sc = new Scanner(System.in);
//            String name = sc.nextLine();
//            System.out.print("Please Enter The Family:");
//            Scanner sc1 = new Scanner(System.in);
//            String family = sc1.nextLine();
//            pA.findByNameAndFamily(name, family);
//
//            System.out.println("The Result Of Your Search With \"Identity\"");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//
//    public static void main(String[] args) throws SQLException {
//        BarrowManagement barrowManagement = new BarrowManagement();
//
//        Test test = new Test();
//       test.findTheMemberWithIdentity();
//
//    }
//
//}
//
//
//
