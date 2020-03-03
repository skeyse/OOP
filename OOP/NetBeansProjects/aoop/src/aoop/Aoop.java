/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aoop;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sean-
 */
public class Aoop {
    
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();
        
        for(int i = 0; i < 2; i++) {
            Person person = new Person();
            person.setName("Name " + i);
            list.add(person);
        }
        
        list.add("Hello World");
        
        for(Object object: list) {
            if(object instanceof Person) {
                Person person = (Person)object;  //Casting!!
                System.out.println(person.getName());
            }
            else
            System.out.println(object.toString());
        }
        
    }
    
}
