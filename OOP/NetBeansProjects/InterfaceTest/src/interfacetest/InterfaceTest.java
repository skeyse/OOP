package interfacetest;

public class InterfaceTest {

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(21);
        System.out.println("Student: " + student.getAge());
        
        Person person = new Student();
        person.setAge(21);
        System.out.println("Person: " + person.getAge());
        
        //System.out.println("The person is old enough: " + person.getOldEnough());
        //doesn't work unless getOldEnough is located inside the interface..
        
        Person person2 = new Instructor();  //easily switch Student in here
        person2.setAge(23);
        System.out.println("Age is: " + person2.getAge());
        System.out.println("The Type is: " + person2.getType());
        
    }
    
}
