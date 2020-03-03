package seantest5;

public class SeanTest5 {

    public static void main(String[] args) {
        Person person = new Person();
        person.setFirstName("Sean");
        person.setLastName("Keyse");
        
        Address address = new Address();
        address.setStreet("5415 Cloisters Drive");
        address.setCity("Canfield");
        address.setState("Ohio");
        address.setZipCode("44406");
        
        person.setAddress(address);
        
        System.out.println(person.toString());
    }
    
}
