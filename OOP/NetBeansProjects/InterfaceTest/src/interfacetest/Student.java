/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacetest;

/**
 *
 * @author Sean-
 */
public class Student implements Person {
    private int age;
    private boolean oldEnough;
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
        setOldEnough(this.age);
    }
    
    private void setOldEnough(int age) {
        if(age >= 21) {
            oldEnough = true;
        }
    }
    
    private boolean getOldEnough() {
        return oldEnough;
    }
    
    public String getType() {
        return "STUDENT";
    }
}
