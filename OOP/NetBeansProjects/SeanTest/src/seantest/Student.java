package seantest;

public class Student extends Person {
 
    private int id;
    
    public Student(String firstName, String lastName, int id) {
        super(firstName, lastName);
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
}
