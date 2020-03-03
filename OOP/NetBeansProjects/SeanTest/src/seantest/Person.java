package seantest;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private static int number;
    
    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        number++;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    protected String getLastName() {
        return lastName;
    }
    
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
    
    public int getYearOfBirth() {
        return 2017 - age;
    }
    
    public static int getNumber() {
        return number;
    }
    
}
