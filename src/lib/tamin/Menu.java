package lib.tamin;


import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    AdminManagement employee = new AdminManagement();
    MembersManagement member = new MembersManagement();

    public void menu() throws SQLException, InputMismatchException {


        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("\t\t   -------------------------------------\n" +
                    "\t\t\t****\"WELCOME TO TAMIN LIBRARY\"**** \n" +
                    "\t\t   -------------------------------------");
            while (true) {
                System.out.println(
                        "\n\t\t\t\t    *PLEASE PRESS A BUTTON* \n" +
                                "\n1:MEMBERS MENU \n" +
                                "2:ADMINISTRATOR MENU  \n" +
                                "3:EXIT ");
                String a = sc.next();
                switch (String.valueOf(a)) {
                    case "1": {
                        member.memberLoginOrSingUp();
                        break;
                    }
                    case "2": {
                        employee.memberLoginOrSingUp();
                        break;
                    }
                    case "3": {
                        return;
                    }
                    default:
                        System.out.println("\n\t\t\"ERROR\"Please Enter A Valid Number From \'1\' To \'3\'");
                }
            }

        } catch (InputMismatchException e) {
            System.out.println(("(\"InputMismatchException\" You Have To Use Digit Numbers ONLY)"));

        }
    }
}



