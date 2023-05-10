package lib.tamin;

import lib.tamin.da.BookDataBase;
import lib.tamin.da.ConnectionTools;
import lib.tamin.da.PersonDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MembersManagement {
    private Connection connection;
    private PreparedStatement statement;
    Person personEmpty = new Person();
    PersonDataBase pA = new PersonDataBase();
    BookDataBase bA = new BookDataBase();
    BarrowManagement barrowManagement = new BarrowManagement();
    Scanner sc = new Scanner(System.in);
    public void membersActions() throws SQLException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("                ****\"MEMBERS MENU\"****\n" +
                    "\n1:View Books In Tamin Library \n" +
                    "2:Barrow A book\n" +
                    "3:Show Issue Books For Each Librarian\n" +
                    "4:Return Book \n" +
                    "5:Search Book\n" +
                    "6:Log Out");
            String b = sc.next();
            switch (String.valueOf(b)) {

                case "1":
                    bA.findAll();
                    break;
                case "2":
                    issueBook();
                    break;
                case "3":
                    viewIssueBooks();
                    break;
                case "4":
                    barrowManagement.returnBook();
                    break;
                case "5":
                    searchTheBook();
                    break;
                case "6":
                    return;

                default:
                    System.out.println("\n\t\t\"ERROR\"Please Enter Valid Number From \'1\' To \'6\'");
            }
        }
    }
    public void searchTheBook() throws SQLException {


        try {
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.println("\n                 ****\"SEARCH FOR BOOK MENU\"****\n" +
                        "1:SEARCH BY BOOK NAME\n" +
                        "2:SEARCH BY WRITER NAME\n" +
                        "3:SEARCH BY BOOK NAME AND WRITER NAME\n" +
                        "4:BACK");
                String a = sc.next();
                switch (String.valueOf(a)) {
                    case "1": {
                        Scanner sc1 = new Scanner(System.in);
                        System.out.print("Please Enter Book Name:");
                        String bookName1 = sc1.nextLine();
                        System.out.println(bA.findByName(bookName1));
                        break;
                    }
                    case "2":
                        Scanner sc1 = new Scanner(System.in);
                        System.out.print("Please Enter Writer Name:");
                        String writerName1 = sc1.nextLine();
                        System.out.println(bA.findByWriter(writerName1));
                        break;
                    case "3":
                        Scanner sc2 = new Scanner(System.in);
                        System.out.print("Please Enter Book Name:");
                        String bookName2 = sc2.nextLine();
                        System.out.print("Please Enter Writer Name:");
                        String writerName2 = sc2.nextLine();
                        System.out.println(bA.findBookNameOrWriter(bookName2, writerName2));
                        break;
                    case "4":
                        return;
                    default:
                        System.out.println("\n\t\t\"ERROR\" Please Enter Valid Number From \'1\' To \'4\'\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            System.out.println(("\"InputMismatchException\" You Have To Use Digit Numbers ONLY)"));
        }
        System.out.println("Please Enter Your Needed Book");
        String bookName = sc.nextLine();
        System.out.println(bA.findByName(bookName));
        System.out.println("Book searching");

    }
    public void issueBook() throws SQLException {//issue book
        barrowManagement.issueRegistration();

        System.out.println("Person Gave This Book");
    }

    public void viewIssueBooks() {
        try {
            System.out.print("Enter Your Membership ID:");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            barrowManagement.findAllIssueBooks(id);
            System.out.println("This Your Issue Books");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void signUp() throws SQLException {
        try {
            System.out.print("Please Enter Your First Name:");
            Scanner sc1 = new Scanner(System.in);
            String newName = sc1.nextLine();
            personEmpty.setName(newName);
            System.out.print("Please Enter Your Family Name:");
            Scanner sc2 = new Scanner(System.in);
            String newFamily = sc2.nextLine();
            personEmpty.setFamily(newFamily);
            System.out.print("Please Enter Your Password:");
            Scanner sc3 = new Scanner(System.in);
            String newPassword = sc3.nextLine();
            personEmpty.setPassword(newPassword);
            Person person = new Person(personEmpty.getName(), personEmpty.getFamily(), personEmpty.getPassword());
            pA.add(person);
            System.out.println("Now You Are A Member Of Tamin Library With This" +
                    " Information:" + person);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    public String findPassword(int id) throws SQLException {
        Connection connection;
        PreparedStatement statement;
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME,PASSWORD");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        result.next();

        return result.getString("password");
    }

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

    public boolean login() throws SQLException, InputMismatchException {
        try {
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Please Enter Your Membership ID:");
                int id = sc.nextInt();
                if (searchIdPerson(id)) {
                    System.out.print("Please Enter Your Password:");
                    Scanner sc1 = new Scanner(System.in);
                    String pass = sc1.next();
                    if (pass.equals(findPassword(id))) {
                        System.out.println("You Logged In Successfully");
                        System.out.println("Profile: " + pA.findByID(id));
                        return true;
                    } else System.out.println("PASSWORD IS WRONG:Please Try Again");
                    break;
                }
                break;
            }
            return false;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            System.out.println("\t\t\t(\"InputMismatchException\" You Have To Use Digit Numbers ONLY)");

        }

        return false;
    }
    public void memberLoginOrSingUp() throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("\n                 ****\"LOGIN OR SIGNUP MENU\"****\n" +
                        "1:LOGIN\n" +
                        "2:SIGN UP\n" +
                        "3:BACK");
                String a = sc.next();
                switch (String.valueOf(a)) {
                    case "1": {
                        System.out.println("1:Login");
                        if (login())
                            membersActions();
                        else
                            System.out.println("\n\t\t\"ERROR\" If You Are Not Library's Member Please Sign Up\n");
                        break;
                    }
                    case "2":
                        System.out.println("2:Signed Up\n");
                        signUp();
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println("\n\t\t\"ERROR\" Please Enter Valid Number From \'1\' To \'3\'\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            System.out.println(("\"InputMismatchException\" You Have To Use Digit Numbers ONLY)"));
        }
    }

}
