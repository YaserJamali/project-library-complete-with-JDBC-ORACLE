package lib.tamin.da;

import lib.tamin.Person;

import java.sql.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;


//jdbc:oracle:thin:@localhost:1521:XE
public class PersonDataBase {
    private Connection connection;
    private PreparedStatement statement;

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public int nextId() throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("select PERSON_SEQ.nextval as id from dual");
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt("id");

    }


    public Person add(Person person) throws SQLException {
        person.setId(nextId());
        connection = ConnectionTools.connection();

        statement = connection.prepareStatement("insert into PERSON (ID,NAME,FAMILY,PASSWORD) VALUES (?,?,?,?)");
        statement.setInt(1, person.getId());
        statement.setString(2, person.getName());
        statement.setString(3, person.getFamily());
        statement.setString(4, person.getPassword());
        int result = statement.executeUpdate();
        close();
        if (result == 1) {
            return person;
        } else {
            return null;
        }
    }

    public void edit(Person person) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("UPDATE PERSON SET NAME=?,FAMILY=?,PASSWORD=? WHERE ID=?");
        statement.setString(1, person.getName());
        statement.setString(2, person.getFamily());
        statement.setString(3, person.getPassword());
        statement.setInt(4, person.getId());
        statement.execute();
        close();
    }

    public void remove(int id) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("DELETE FROM PERSON  WHERE ID=?");
            statement.setInt(1, id);
            statement.execute();
            connection.close();
        } catch (NullPointerException | SQLException e) {
            System.out.println("****This IS A EXCEPTION**** \nThere Is No Member With This ID: " + e.getMessage());
        }
    }

    public List findByID(int id) {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT * FROM PERSON WHERE ID=? ORDER BY FAMILY,NAME");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            List list = new ArrayList<>();
            while (result.next()) {
                list.add("UserName:" + result.getInt("ID"));
                list.add("Name:" + result.getString("NAME"));
                list.add("Family:" + result.getString("FAMILY"));
                list.add("Password:" + result.getString("PASSWORD"));
            }
            statement.close();
            connection.close();
            return list;
        } catch (SQLException | InputMismatchException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void removeMemberByNameAndFamily(String name, String family) throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("DELETE FROM PERSON  WHERE NAME=? AND FAMILY=?");
            statement.setString(1, name);
            statement.setString(2, family);
            statement.execute();
            connection.close();
        } catch (NullPointerException e) {
            System.out.println("There Is No Member With This Name Or Family " + e.getMessage());
        }
    }

    public List<Person> showAll() throws SQLException {

        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM PERSON  ORDER BY FAMILY,NAME");
        ResultSet result = statement.executeQuery();
        List<Person> list = new ArrayList<>();
        while (result.next()) {
            Person person = new Person(result.getInt("id"),
                    result.getString("name"),
                    result.getString("family"),
                    result.getString("password"));
            list.add(person);
        }
        close();
        return list;
    }

    public void findByNameAndFamily(String name, String family) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM PERSON WHERE NAME LIKE ? and FAMILY LIKE ? ORDER BY FAMILY,NAME");
        statement.setString(1, "%" + name + "%");
        statement.setString(2, "%" + family + "%");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            System.out.println("\n" + result.getInt("ID"));
            System.out.println(result.getString("NAME"));
            System.out.println(result.getString("FAMILY"));
        }
        close();
    }
}//end of class





