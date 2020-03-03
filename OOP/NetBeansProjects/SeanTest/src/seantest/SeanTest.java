package seantest;

public class SeanTest {

    public static void main(String[] args) {
        Person person = new Person("Sean", "Keyse");
        
        Student student = new Student("Sean", "Keyse", 365);
        
        student.setAge(23);
        
        System.out.println("ID = " + student.getId());
        System.out.println("First Name = " + student.getFirstName());
        System.out.println("Last Name = " + student.getLastName());
        
        System.out.println("Age: " + student.getAge());
        System.out.println("Year Born: " + student.getYearOfBirth());
        
        System.out.println("Number of People: " + Person.getNumber());
    }
    
}
