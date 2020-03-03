package seantest3;

public class Person {
    
    private String firstName;
    private String lastName;
    private int age;
    private int birthYear;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setAge(int age, boolean setBirthYear) {
        setAge(age);
        if (setBirthYear) {
            birthYear = 2017 - age;
        }
    }
    
    public Person(String firstName) {
        setFirstName(firstName);
    }
    
    public Person(String firstName, String lastName) {
        this(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
   // public abstract String getFullName();
    //public abstract String getType();
    
    public void printInfo() {
        System.out.println("Name: " + firstName + " " + lastName);
        //System.out.println("Type is: " + getType());
        System.out.println("Age: " + age);
        System.out.println("Birth Year: " + birthYear);
    }
    
}
