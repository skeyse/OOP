package testapp;

public class Person {
    private int age;
    private boolean oldEnough = false;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        //if(age >= 21) {
            //oldEnough = true;
        //}
    }
    
    public boolean getOldEnough() {
        return oldEnough;
    }
    
    private void setOldEnough() {
        if(age >= 21) {
            oldEnough = true;
        }
    }
    
}
