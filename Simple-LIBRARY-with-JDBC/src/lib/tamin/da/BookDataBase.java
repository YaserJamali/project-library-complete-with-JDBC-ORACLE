package lib.tamin.da;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import lib.tamin.Book;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


//jdbc:oracle:thin:@localhost:1521:XE
public class BookDataBase {
    private Connection connection;
    private PreparedStatement statement;

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public int nextId() throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("select BOOK_SEQ.nextval as id from dual");
        ResultSet result = statement.executeQuery();
        result.next();
        return result.getInt("id");

    }

    public void edit(Book book) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("UPDATE BOOK SET BOOK_NAME=?,WRITER=?,PUBLISHER=? WHERE ID=?");
        statement.setString(1, book.getBookName());
        statement.setString(2, book.getWriterName());
        statement.setString(3, book.getPublisher());
        statement.execute();
        close();
    }

    public void remove(int id) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("DELETE FROM BOOK WHERE ID=?");
        statement.setInt(1, id);
        statement.execute();
        close();
    }

    public List findByID(int id) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT BOOK_NAME,WRITER,PUBLISHER from BOOK WHERE ID=?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        List list = new ArrayList<>();
        while (result.next()) {
            list.add(result.getString("BOOK_NAME"));
            list.add(result.getString("WRITER"));
            list.add(result.getString("PUBLISHER"));

        }
        statement.close();
        connection.close();
        if (list.size() != 0)
            return list;
        else System.out.println("There Is No Match For Your Search");
        return null;
    }

    public List findByName(String bookName) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM BOOK WHERE BOOK_NAME LIKE ?  ORDER BY BOOK_NAME,WRITER");
        statement.setString(1, "%" + bookName + "%");

        ResultSet result = statement.executeQuery();
        List list = new ArrayList();
        while (result.next()) {
            list.add(result.getInt("ID"));
            list.add(result.getString("BOOK_NAME"));
            list.add(result.getString("WRITER"));
            list.add(result.getString("PUBLISHER"));
            list.add("\n");
        }
        statement.close();
        connection.close();
        if (list.size() != 0)
            return list;
        else System.out.println("There Is No Match For Your Search");
        return null;
    }

    public List findBookNameOrWriter(String bookName, String writer) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM BOOK WHERE BOOK_NAME LIKE ? and WRITER LIKE ? ORDER BY BOOK_NAME,WRITER");
        statement.setString(1, "%" + bookName + "%");
        statement.setString(2, "%" + writer + "%");
        ResultSet result = statement.executeQuery();
        List list = new ArrayList();
        while (result.next()) {
            list.add(result.getInt("ID"));
            list.add(result.getString("BOOK_NAME"));
            list.add(result.getString("WRITER"));
            list.add(result.getString("PUBLISHER"));
            list.add("\n");
        }
        System.out.println("The Book '" + list.get(1) + "' Wrote By '" + list.get(2) + "' And Published By '" + list.get(3) + "'");
        statement.close();
        connection.close();
        if (list.size() != 0)
            return list;
        else System.out.println("There Is No Match For Your Search");
        return null;
    }


    public void findAll() throws SQLException {
        try {
            connection = ConnectionTools.connection();
            statement = connection.prepareStatement("SELECT * FROM BOOK ORDER BY id");
            List list = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println("Book Id:'" + (result.getInt("ID")) +
                        "'\tBook Name:'" + (result.getString("BOOK_NAME"))
                        + "'\tWriter:'" + (result.getString("WRITER")) +
                        "'\tPublished By:'" + (result.getString("PUBLISHER")) + "'\n");

            }

            close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    public List findByWriter(String writer) throws SQLException {
        connection = ConnectionTools.connection();
        statement = connection.prepareStatement("SELECT * FROM BOOK WHERE WRITER LIKE ?  ORDER BY BOOK_NAME,WRITER");
        statement.setString(1, "%" + writer + "%");

        ResultSet result = statement.executeQuery();
        List list = new ArrayList<>();
        while (result.next()) {

            list.add(result.getInt("ID"));
            list.add(result.getString("BOOK_NAME"));
            list.add(result.getString("WRITER"));
            list.add(result.getString("PUBLISHER"));
            list.add("\n");
        }
        if (list.size() != 0)
            return list;
        else System.out.println("There Is No Match For Your Search");
        return null;
    }

    public Book add(Book book) throws SQLException {
        book.setId(nextId());
        connection = ConnectionTools.connection();

        statement = connection.prepareStatement("insert into BOOK (ID,BOOK_NAME,WRITER,PUBLISHER) VALUES (?,?,?,?)");
        statement.setInt(1, book.getId());
        statement.setString(2, book.getBookName());
        statement.setString(3, book.getWriterName());
        statement.setString(4, book.getPublisher());
        int result = statement.executeUpdate();
        close();
        if (result == 1) {
            return book;
        } else {
            return null;
        }
    }
}
