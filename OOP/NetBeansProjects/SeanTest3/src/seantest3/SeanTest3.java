package seantest3;

public class SeanTest3 {

    public static void main(String[] args) {
        
        Person person = new Person("John");
        person.setAge(21);
        Person personTwo = new Person("Sean", "Keyse");
        personTwo.setAge(23, true);
        person.printInfo();
        personTwo.printInfo();
    }
    
}
