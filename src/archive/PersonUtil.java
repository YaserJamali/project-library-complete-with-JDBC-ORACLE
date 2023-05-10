//package tamin.library;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class PersonUtil extends Person<String> {
//    List<Person> persons = new ArrayList<>();
//    Person person = new Person();
//
//
//    public boolean continueInMenu() {
//        Scanner sc = new Scanner(System.in);
//        boolean result = false;
//        while (!result) {
//            System.out.print("Do you Want to Continue:");
//            String answer = sc.next();
//            if (answer.equalsIgnoreCase("Yes")) {
//                result = true;
//            } else if (answer.equalsIgnoreCase("No")) {
//                result = false;
//                break;
//            } else System.out.println("\"ERROR\" Please Enter Yes or No");
//        }//end of while
//        return result;
//    }//end of method continueInMenu()
//
//
//    public void register() {
//
//        int count = 0;
//        while (true) {
//            person.setId(count++);
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Please Enter Your Name:");
//            String name = sc.next();
//            person.setName(name);
//            System.out.println("Please Enter Your Family:");
//            String family = sc.next();
//            person.setFamily(family);
//            System.out.println("Please Enter Your National ID:");
//            String nationalId = sc.next();
//            person.setNationalId(nationalId);
//
//            File file = new File("dataClass.txt");
//            Writer fw;
//
//            PersonUtil personUtil = new PersonUtil();
//
//            try {
//                fw = new FileWriter(file, true);
//                fw.write(person.toString());
//                fw.flush();
//                fw.close();
//
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//
//            if (!continueInMenu()) break;
//        }
//
//        persons.add(person);
//
//    }//end of while
//
//}
//
