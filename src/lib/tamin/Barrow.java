package lib.tamin;

import java.sql.Date;
import java.time.LocalDate;

public class Barrow {
    private int barrowId;
    private int memberId;


    private String bookName;


    private Date returnDay;

    public Barrow() {
    }


    public Barrow(int memberId, String bookName, Date returnDay) {
        this.memberId = memberId;
        this.bookName = bookName;
        this.returnDay = returnDay;
    }

    public Barrow(int barrowId, int memberId, String bookName, Date returnDay) {
        this.barrowId = barrowId;
        this.memberId = memberId;
        this.bookName = bookName;
        this.returnDay = returnDay;
    }

    public int getBarrowId() {
        return barrowId;
    }

    public Barrow setBarrowId(int barrowId) {
        this.barrowId = barrowId;
        return this;
    }

    public int getMemberId() {
        return memberId;
    }

    public Barrow setMemberId(int memberId) {
        this.memberId = memberId;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Barrow setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Date getReturnDay() {
        return returnDay;
    }

    public Barrow setReturnDay(Date returnDay) {
        this.returnDay = returnDay;
        return this;
    }


    @Override
    public String toString() {
        return "Barrow{" +
                "barrowId=" + barrowId +
                ", memberId=" + memberId +
                ", bookName='" + bookName + '\'' +
                ", returnDay=" + returnDay +
                '}'+"\n";
    }
}
