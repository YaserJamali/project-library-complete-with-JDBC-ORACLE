package lib.tamin;

import lib.tamin.da.ConnectionTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BarrowManagement {

    private Connection connection;
    private PreparedStatement statement;

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public boolean searchBookNameExactly(String name) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE BOOK_NAME=?");
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();
            if (result.next() && name.equals(result.getString("BOOK_NAME"))) {
                System.out.println("The Book Name Found Has Found:\"" + name + "\"");
                return true;
            } else {
                System.out.println("There Is No Book Name With This Book Name You Searched:" + name);
                return false;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
                    + e.getMessage());
        }
        return false;
    }

    public void issueRegistration() throws SQLException {
        try {
            Scanner sc = new Scanner(System.in);
            Barrow barrowEmpty = new Barrow();
            System.out.print("Please Enter Your Membership id:");
            int id = sc.nextInt();
            System.out.print("Please Enter The Demand Book Name:");
            Scanner sc1 = new Scanner(System.in);
            String bookName = sc1.nextLine();
            if (searchId(id))
                if (searchBookNameExactly(bookName)) {
                    barrowEmpty.setMemberId(id);
                    barrowEmpty.setBookName(bookName);
                    System.out.print("Please The Year For Return:");
                    int year = sc.nextInt();
                    System.out.print("Please The Month For Return:");
                    int month = sc.nextInt();
                    System.out.print("Please The Day For Return:");
                    int day = sc.nextInt();
                    Date date = new Date(year, month, day);
                    barrowEmpty.setReturnDay(date);


                    Barrow barrow = new Barrow(barrowEmpty.getMemberId(), barrowEmpty.getBookName(), barrowEmpty.getReturnDay());
                    add(barrow);
                    System.out.println("You Issued The Book With This" +
                            " Information:" + barrow);
                }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean searchId(int id) throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next() && id == result.getInt(1)) {
            System.out.println("The ID Has Found");
            return true;
        } else {
            System.out.println("\"ERROR\" The Id Has Not Found!!!");
            return false;
        }
    }


    public boolean searchName(String name) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT BOOK_NAME,WRITER,PUBLISHER FROM BOOK WHERE BOOK_NAME like ? ORDER BY BOOK_NAME,WRITER,PUBLISHER");
            statement.setString(1, "%" + name + "%");

            ResultSet result = statement.executeQuery();
            if (result.next() && result.getString(1).contains(name)) {
                System.out.println("The Book Name Found Has Found:\"" + result.getString(1) + "\"");
                return true;
            } else {
                System.out.println("There Is No Book Name With This Book NAme You Searched:" + name);
                return false;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
                    + e.getMessage());
        }
        return false;
    }

    public int nextId() throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("select BARROW_SEQ.nextval as id from dual");
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt("id");

    }

    public Barrow add(Barrow barrow) throws SQLException {
        barrow.setBarrowId(nextId());
        connection = ConnectionTools.connection();

        statement = connection.prepareStatement("insert into BARROW (BARROW_ID,PERSON_ID,BOOK_NAME,RETURN_DATE) VALUES (?,?,?,?)");
        statement.setInt(1, barrow.getBarrowId());
        statement.setInt(2, barrow.getMemberId());
        statement.setString(3, barrow.getBookName());
        statement.setDate(4, barrow.getReturnDay());
        int result = statement.executeUpdate();
        close();
        if (result == 1) {
            return barrow;
        } else {
            return null;
        }

    }//end of barrow method

    public boolean searchIdPerson(int id) throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next() && id == result.getInt(1)) {
            System.out.println("The ID " + id + " Found By This Identity \"" + result.getString("NAME") + " " + result.getString("FAMILY") + "\"");
            return true;
        } else {
            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
            return false;
        }
    }

    public void removeBar(int id) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("DELETE FROM BARROW  WHERE BARROW_ID=?");
            statement.setInt(1, id);
            statement.execute();
            connection.close();
        } catch (NullPointerException e) {
            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
        }
    }

    public boolean searchIdIssueBook(int id) throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM BARROW WHERE BARROW_ID=? ORDER BY BOOK_NAME,PERSON_ID,RETURN_DATE");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next() && id == result.getInt(2)) {
            System.out.println("The ID Has Found");
            return true;
        } else {
            System.out.println("\"ERROR\" The Id Has Not Found!!!");
            return false;
        }
    }

    public void returnBook() throws SQLException {
        System.out.print("Please Enter Your Issue's Book ID:");
        Scanner sc = new Scanner(System.in);
        int issueId = sc.nextInt();
        if (searchIdIssueBook(issueId)) {
            removeBar(issueId);
            System.out.println("You Returned The Book With This ID \"" + issueId + "\" Successfully");

        }

    }


    public List findAllIssueBooks(int personId) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT PERSON.ID,NAME,FAMILY,BOOK.BOOK_NAME,WRITER,PUBLISHER,RETURN_DATE,BARROW_ID FROM PERSON,BOOK,BARROW WHERE PERSON_ID=?  and PERSON.ID=BArrow.PERSON_ID and BOOK.BOOK_NAME=BARROW.BOOK_NAME ORDER BY PERSON_ID");
            statement.setInt(1, personId);
            ResultSet result = statement.executeQuery();
            List list = new ArrayList<>();
            int count = 0;
            while (result.next()) {

                list.add(result.getInt("ID"));
                list.add(result.getString("NAME"));
                list.add(result.getString("FAMILY"));
                list.add(result.getString("BOOK_NAME"));
                list.add(result.getString("WRITER"));
                list.add(result.getString("PUBLISHER"));
                list.add(result.getInt("BARROW_ID"));
                list.add("\n");
                count++;
            }
            System.out.println("'" + list.get(1) + " " + list.get(2) + "' Gave '" + count + "' Books From Tamin Library");
            System.out.println(list);
            close();
            if (list.size() != 0)
                return list;
            else System.out.println("There Is No Match For Your Search");
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage() + "'This Id Is Not Exist Any More Or This Book Is Not Exist In Tamin library'");
        }
        return null;
    }

    public boolean searchPersonIdFromBarrow(int id) throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT PERSON_ID FROM BARROW WHERE PERSON_ID=? ");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next() && id == result.getInt(1)) {
            System.out.println("The Person's ID " + id + " Found In Issues Book ");
            return true;

        } else {
            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
            return false;
        }
    }

    public void removePersonWithIdFromBarrowId(int id) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("DELETE FROM BARROW  WHERE PERSON_ID=?");
            statement.setInt(1, id);
            statement.execute();
            connection.close();
        } catch (NullPointerException e) {
            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
        }
    }


}//end of barrowManagement class
