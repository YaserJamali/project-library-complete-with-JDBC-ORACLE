package lib.tamin;

import lib.tamin.da.BookDataBase;
import lib.tamin.da.ConnectionTools;
import lib.tamin.da.PersonDataBase;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminManagement {
    private Connection connection;
    private PreparedStatement statement;

    BookDataBase bA = new BookDataBase();
    PersonDataBase pA = new PersonDataBase();
    Print print = new Print();
    BarrowManagement barrowManagement = new BarrowManagement();

    public void employeeActions() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n                 ****\"ADMIN MENU\"****\n" +
                            "1:Add A Book\n" +
                            "2:Edit The Book\n" +
                            "3:Remove The Book \n" +
                            "4:Show The Book's Information\n" +
                            "5:Search A Member\n" +
                            "6:Edit A Member\n" +
                            "7:Remove A Member\n" +
                            "8:Show All Librarians\n" +
                            "9:Print\n" +
                            "10:Log Out");
            String b = sc.next();
            switch (String.valueOf(b)) {
                case "1":
                    addBook();
                    break;
                case "2":
                    editBook();
                    break;
                case "3":
                    removeTheBook();
                    break;
                case "4":
                    findTheBook();
                    break;
                case "5":
                    findTheMember();
                    break;
                case "6":
                    editMember();
                    break;
                case "7":
                    removeMember();
                    break;
                case "8":
                    showAllLibrarians();
                    break;
                case "9":
                    print.printMenu();
                    break;
                case "10":
                    return;
                default:
                    System.out.println("Please Enter Valid Number From \'1\' To \'10\'");

            }
        }
    }

    public void removeTheBook() {
        System.out.println("The Book Removed By ADMIN");

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("\n\n                ****\"REMOVE MENU\"****\n");
            System.out.println("1:REMOVE By \"ID\"\n" +
                    "2:REMOVE By \"Book Name\"\n" +
                    "3:Back");
            String a = sc.next();
            switch (String.valueOf(a)) {
                case "1":
                    removeBookById();
                    break;
                case "2":   //****
                    removeBookWithBookName();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Please Enter Valid Number From \'1\' To \'3\'");
            }
        }
    }

    public void findTheBook() {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("                ****\"BOOKS SEARCH MENU\"****\n");
            System.out.println("1:Search By \"ID\"\n" +
                    "2:Search By \"Identity\"\n" +
                    "3:Back");
            String a = sc.next();
            switch (String.valueOf(a)) {
                case "1":
                    findTheBookWithId();
                    break;
                case "2":   //****
                    findBookWithBookName();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Please Enter Valid Number From \'1\' To \'3\'");
            }
        }

    }

    public void removeMember() throws SQLException {
        try {
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("                ****\"REMOVE PERSON MENU\"****\n");
                System.out.println("1:REMOVE By \"ID\"\n" +
                        "2:REMOVE By \"IDENTITY\"\n" +
                        "3:Back");
                String a = sc.next();
                switch (String.valueOf(a)) {
                    case "1":
                        removeTheMemberById();
                        break;
                    case "2":
                        Scanner sc1 = new Scanner(System.in);
                        System.out.print("Please Enter Name And Family Of Member:");
                        String name = sc1.nextLine();
                        System.out.print("Please Enter Name And Family Of Member:");
                        String family = sc1.nextLine();
                        pA.removeMemberByNameAndFamily(name, family);
                        System.out.println("Member Has Deleted");
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println("Please Enter Valid Number From \'1\' To \'3\'");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("There Is No Record For This Identity" + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Please Enter Only Digit Numbers: " + e.getMessage());
        }


    }//end of removeMember() method


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

    public void removeTheMemberById() {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.print("Please Enter Member ID:");
            int id = sc.nextInt();
            if (searchIdPerson(id)) {
                pA.remove(id);
                System.out.println("Member: " + id + " Removed");
                if (barrowManagement.searchPersonIdFromBarrow(id))
                    barrowManagement.removePersonWithIdFromBarrowId(id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
                    + e.getMessage());
        }
    }

    public void findTheMember() throws SQLException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("                ****\"MEMBER SEARCH MENU\"****\n");
            System.out.println("1:Search By \"ID\"\n" +
                    "2:Search By \"Identity\"\n" +
                    "3:Back");
            String a = sc.next();
            switch (String.valueOf(a)) {
                case "1":
                    System.out.print("Please Enter Member ID: ");
                    Scanner sc1 = new Scanner(System.in);
                    int id = sc1.nextInt();
                    if (searchId(id))
                        System.out.println(pA.findByID(id));
                    break;
                case "2":
                    findTheMemberWithIdentity();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Please Enter Valid Number From \'1\' To \'3\'");
            }
        }


    }

    public boolean searchId(int id) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next() && id == result.getInt(1)) {
                System.out.println("The Id Has Found");
                return true;
            } else {
                System.out.println("There Is No MEMBER ID With This ID:" + id);
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

    public void addBook() throws SQLException {
        try {
            Scanner sc = new Scanner(System.in);
            Book bookEmpty = new Book();
            BookDataBase bA = new BookDataBase();
            System.out.print("Please Enter The Book's Name:");
            String bookNAme = "" + sc.nextLine();
            bookEmpty.setBookName(bookNAme);
            System.out.print("Please Enter The Writer Name:");
            String writerName = sc.nextLine();
            bookEmpty.setWriterName(writerName);
            System.out.print("Please Enter The Publisher Name:");
            String publisherNAme = sc.nextLine();
            bookEmpty.setPublisher(publisherNAme);
            Book book = new Book(bookEmpty.getBookName(), bookEmpty.getWriterName(), bookEmpty.getPublisher());
            bA.add(book);
            System.out.println("The Book Has Added With This Information::" + book);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    public void editMember() {
        try {
            Scanner sc = new Scanner(System.in);
            Person personEmpty = new Person();
            System.out.print("Please Enter The Member ID:");
            int id = sc.nextInt();
            if (searchId(id)) {
                System.out.println(bA.findByID(id));
                Scanner sc1 = new Scanner(System.in);
                personEmpty.setId(id);
                System.out.print("What IS The New Name:");
                String name = "" + sc1.nextLine();
                personEmpty.setName(name);
                System.out.print("What IS The New Family:");
                personEmpty.setFamily(sc1.nextLine());
                System.out.print("What IS The New Password:");
                personEmpty.setPassword(sc1.nextLine());

                Person person = new Person(personEmpty.getId(), personEmpty.getName(), personEmpty.getFamily(), personEmpty.getPassword());
                PersonDataBase pA = new PersonDataBase();
                pA.edit(person);
                System.out.println(person);
            }
        } catch (SQLException e) {
            System.out.println("\"ERROR\" Please Use 20 Characters For Each Field!!!: " + e.getMessage());
        }


    }


    public boolean searchIdBook(int id) throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID=? ORDER BY ID");
        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        if (result.next() && id == result.getInt(1)) {
            System.out.println("The Book's ID " + id + " Found The Book '" + result.getString("BOOK_NAME") + "' Who Wrote \"" + result.getString("WRITER") + "\"");
            return true;
        } else {
            System.out.println("\"ERROR\" This '" + id + "' Id Has Not Found!!!");
            return false;
        }
    }


    public void editBook() {
        try {
            Scanner sc = new Scanner(System.in);
            Book bookEmpty = new Book();
            System.out.print("Please Enter The Book's ID:");
            int id = sc.nextInt();
            if (searchIdBook(id)) {
                Scanner sc1 = new Scanner(System.in);
                bookEmpty.setId(id);
                System.out.print("What IS The New Name For This Book:");
                String bookNAme = "" + sc1.nextLine();
                bookEmpty.setBookName(bookNAme);
                System.out.print("What IS New Writer Name:");
                bookEmpty.setWriterName(sc1.nextLine());
                System.out.print("What IS New Publisher Name:");
                bookEmpty.setPublisher(sc1.nextLine());

                Book book = new Book(bookEmpty.getId(), bookEmpty.getBookName(),
                        bookEmpty.getWriterName(), bookEmpty.getPublisher());
                BookDataBase bA = new BookDataBase();
                bA.edit(book);
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("\"ERROR\" Please Use 20 Characters For Each Field!!!: "
                    + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\"ERROR\" Please Only Use Digit Numbers!!!: "
                    + e.getMessage());

        }
    }


    public void findTheBookWithId() {
        try {

            System.out.print("Enter The Id Of Book:");
            Scanner sc1 = new Scanner(System.in);
            int id = sc1.nextInt();
            if (searchIdBook(id)) {
                System.out.println("The Result Of Your Search With ID '" + id + "'");
                System.out.println(bA.findByID(id));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Please Enter Only Digit Numbers" + e.getMessage());
        }
    }

    public void findTheMemberWithIdentity() {
        try {
            System.out.print("Please Enter The Name:");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            System.out.print("Please Enter The Family:");
            Scanner sc1 = new Scanner(System.in);
            String family = sc1.nextLine();
            pA.findByNameAndFamily(name, family);

            System.out.println("The Result Of Your Search With \"Identity\"");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBookById() {
        try {
            System.out.print("Enter The Book's ID:");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            if (searchIdBook(id)) {
                bA.remove(id);
                System.out.print(" Is Removed");
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findBookWithBookName() {
        try {
            System.out.print("Enter The Book Name:");
            Scanner sc1 = new Scanner(System.in);
            String bookName = sc1.nextLine();
            System.out.print("Enter The Writer Name:");
            Scanner sc2 = new Scanner(System.in);
            String writer = sc2.nextLine();
            bA.findBookNameOrWriter(bookName, writer);
        } catch (SQLException | NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllLibrarians() {
        try {
            System.out.println(pA.showAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void memberLoginOrSingUp() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n                 ****\"LOGIN OR SIGNUP MENU\"****\n" +
                    "1:LOGIN\n" +
                    "2:HELP\n" +
                    "3:BACK");
            String a = sc.next();
            switch (String.valueOf(a)) {
                case "1": {
                    System.out.print("Please Enter Your Administrator ID:");
                    String managerUserName = sc.next();
                    System.out.print("Please Enter Your Administrator PASSWORD:");
                    String managerPassword = sc.next();
                    if (managerUserName.equals("ALI") && managerPassword.equals("JAMALI"))
                        employeeActions();
                    else
                        System.out.println("\n\t\t\"ERROR\" Username Or Password Was Wrong\n");
                    break;
                }
                case "2":
                    System.out.println("\t\t\"\"HINT\"\"");
                    System.out.println("Administrator Username is:\'ALI\'\nAdministrator Password is:\'JAMALI\'");
                    break;
                case "3":
                    return;
                default:
                    System.out.println("\n\t\t\"ERROR\" Please Enter Valid Number From \'1\' To \'3\'\n");
            }
        }
    }


    public void removeBookWithBookName() {
        try {
            System.out.print("Enter The Book Name:");
            Scanner sc = new Scanner(System.in);
            String bookName = sc.nextLine();
            if (searchBookNameExactly(bookName)) {
                {
                    removeBookWithBookName(bookName);
                    System.out.println("The Book '" + bookName + "' Removed Successfully From Tamin Library");
                }
                if (searchBookNameExactlyFromBarrow(bookName)) {
                    removeBookWithBookNameFromBarrow(bookName);
                    System.out.println("The Book '" + bookName + "' Removed Successfully From Issued Books");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean searchBookNameExactlyFromBarrow(String name) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT * FROM BARROW WHERE BOOK_NAME=?");
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();
            if (result.next() && name.equals(result.getString("BOOK_NAME"))) {
                System.out.println("The Book Name Found Has Found:\"" + name + "\" In Issues Book");
                return true;
            } else {
                System.out.println("There Is No Book Name With This Book Name You Searched:" + name + " In Issues Book");
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

    public void removeBookWithBookNameFromBarrow(String bookName) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("DELETE FROM BARROW WHERE BOOK_NAME=?");
        statement.setString(1, bookName);
        statement.execute();
        statement.close();
        connection.close();
    }

    public void removeBookWithBookName(String bookName) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("DELETE FROM BOOK WHERE BOOK_NAME=?");
        statement.setString(1, bookName);
        statement.execute();
        statement.close();
        connection.close();
    }
}
