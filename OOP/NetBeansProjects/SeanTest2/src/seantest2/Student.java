/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seantest2;

/**
 *
 * @author Sean-
 */
public class Student implements Grade, Person {
    
    @Override
    public String getFirstName() {
        return "Sean";
    }
    
    @Override
    public String getLastName() {
        return "Keyse";
    }
    
    @Override
    public int getGrade() {
        return 100;
    }
    
}
