/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homeworkone;

/**
 *
 * @author Sean-
 */
public class Teacher extends Student {

    @Override
    void enroll() {
        if(isEnrolled() == false)
            setEnrolled(true);
        else
            setEnrolled(false);
    }

    @Override
    void change_major(String major) {
        setMajor("Communications");
    }
    
    void change_major() {
        setMajor("Undecided");
    }
    
    
    
}
