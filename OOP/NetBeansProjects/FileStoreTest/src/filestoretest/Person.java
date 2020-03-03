/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filestoretest;

public class Person implements CSV {
    String firstName;
    String lastName;
    int age;
    
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    @Override
    public String toCSV() {
        return firstName + "," + lastName + "," + age;
    }
}
