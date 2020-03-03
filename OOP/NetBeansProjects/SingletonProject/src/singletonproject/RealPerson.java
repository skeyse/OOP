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
public class RealPerson implements Person {

    @Override
    public String getFirstName() {
        return "John";
    }

    @Override
    public String getLastName() {
        return "Smith";
    }
    
    
}
