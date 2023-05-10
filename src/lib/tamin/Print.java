package lib.tamin;

import lib.tamin.da.ConnectionTools;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Print {
    private Connection connection;
    private PreparedStatement statement;

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public List<Person> showAllMembers() throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM PERSON  ORDER BY ID");
        ResultSet result = statement.executeQuery();
        List<Person> list = new ArrayList<>();
        while (result.next()) {
            Person person = new Person(result.getInt("id"), result.getString("name"), result.getString("family"),
                    result.getString("password"));
            list.add(person);
        }
        close();
        return list;
    }


    public List<Book> showAllLibrary() throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT * FROM BOOK ORDER BY id");
            List list = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Book book = new Book(result.getInt("id"), result.getString("BOOK_NAME")
                        , result.getString("WRITER"),
                        result.getString("PUBLISHER"));
                list.add(book);

            }
            close();
            return list;

        } catch (NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Barrow> showAllBarrowBooks() throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM BARROW  ORDER BY BARROW_ID");
        ResultSet result = statement.executeQuery();
        List<Barrow> list = new ArrayList<>();
        while (result.next()) {
            Barrow barrow = new Barrow(result.getInt("BARROW_ID"), result.getInt("PERSON_ID"), result.getString("BOOK_NAME"),
                    result.getDate("RETURN_DATE"));
            list.add(barrow);
        }
        close();
        return list;
    }

    public void printMembers() {
        try {
            File file = new File("members.txt");
            OutputStream oS = Files.newOutputStream(file.toPath());
            List list = new ArrayList();


            list.add(showAllMembers());
            oS.write(String.valueOf(list).getBytes());
            oS.flush();
            oS.close();
            System.out.println("Print Member Successfully Completed In members.txt File");
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printBooks() throws IOException, SQLException {
        try {
            File file = new File("books.txt");
            OutputStream oS = Files.newOutputStream(file.toPath());
            List list = new ArrayList();


            list.add(showAllLibrary());
            oS.write(String.valueOf(list).getBytes());
            oS.flush();
            oS.close();
            System.out.println("Print The Books In Tamin Library Successfully Completed In books.txt File");
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printBarrowBooks() throws IOException, SQLException {
        try {
            File file = new File("issues.txt");
            OutputStream oS = Files.newOutputStream(file.toPath());
            List list = new ArrayList();


            list.add(showAllBarrowBooks());
            oS.write(String.valueOf(list).getBytes());
            oS.flush();
            oS.close();
            System.out.println("Print The Issue Books In Tamin Library Successfully Completed In issues.txt File");
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void printMenu() {
        try {
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n\n                ****\"PRINT MENU\"****\n");
                System.out.println("1:PRINT \"TAMIN'S LIBRARIAN\"\n" +
                        "2:PRINT \"TAMIN LIBRARY BOOKS\"\n" +
                        "3:PRINT \"TAMIN ISSUE BOOKS\"\n" +
                        "4:Back");
                String a = sc.next();
                switch (String.valueOf(a)) {
                    case "1":
                        printMembers();
                        break;
                    case "2":   //****
                        printBooks();
                        break;
                    case "3":   //****
                        printBarrowBooks();
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("Please Enter Valid Number From \'1\' To \'4\'");
                }
            }
        } catch (SQLException | IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}
