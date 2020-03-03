package interfacetest;

public interface Person {
    default String getType() {
        return "PERSON";
    }
    void setAge(int age);
    int getAge();
    
    
    //boolean setOldEnough();
    //boolean getOldEnough();
    
    
}
