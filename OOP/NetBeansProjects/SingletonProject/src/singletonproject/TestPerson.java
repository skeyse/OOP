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
public class TestPerson implements Person {

    @Override
    public String getFirstName() {
        return "Test";    
    }

    @Override
    public String getLastName() {
        return "Test";
    }
    
    
}
