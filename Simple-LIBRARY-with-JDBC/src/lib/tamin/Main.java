package lib.tamin;

import java.sql.SQLException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args)  {
        try {
            Menu menu = new Menu();
            menu.menu();
        } catch (SQLException | NullPointerException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
