
package interfacetest;

public class Instructor implements Person {

    private int age;
    
    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }
    
    //public String getType() {
    //    return "INSTRUCTOR";
    //}
    
}
