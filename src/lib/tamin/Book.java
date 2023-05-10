package lib.tamin;

public class Book {
    private int id;
    private String bookName;
    private String writerName;
    private String publisher;

    public Book() {
    }

    public Book(String bookName, String writerName, String publisher) {
        this.bookName = bookName;
        this.writerName = writerName;
        this.publisher = publisher;
    }

    public Book(int id, String bookName, String writerName, String publisher) {
        this.id = id;
        this.bookName = bookName;
        this.writerName = writerName;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getWriterName() {
        return writerName;
    }

    public Book setWriterName(String writerName) {
        this.writerName = writerName;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", writerName='" + writerName + '\'' +
                ", publisher='" + publisher + '\'' +
                '}'+"\n";
    }


}
