package lib.tamin;

import java.util.concurrent.locks.ReentrantLock;

public class Person {
    private int id;
    private String name;
    private String family;
    private String password;

    public Person() {
    }

    public Person(int id, String name, String family, String password) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.password = password;
    }

    public Person(String name, String family, String password) {
        this.name = name;
        this.family = family;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Person setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public Person setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }


    @Override
    public String toString() {
        return "Person(" +
                "username=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ')' + "\n";
    }
}
