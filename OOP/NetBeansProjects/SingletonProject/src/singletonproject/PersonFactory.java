/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singletonproject;

/**
 *
 * @author Sean-
 */
public class PersonFactory {
    private static PersonFactory instance;
    private Person person;
    
    
    private PersonFactory() {}
    
    public static PersonFactory getInstance() {
        if (instance == null)
            instance = new PersonFactory();
        
        return instance;
    }
    
    public Person getPerson(boolean isTest) {
        if (isTest) {
            person = new TestPerson();
        }
        else {
            person = new RealPerson();
        }
        return person;
    }
    
}
