package threadqueueexample;

public class PeopleAnalytics {
    private static PeopleAnalytics INSTANCE;
    
    private int firstNameCount;
    private int lastNameCount;
    
    public void addToFirstNameCount(int count) {
        firstNameCount += count;
    }
    
    public void addToLastNameCount(int count) {
        lastNameCount += count;
    }
    @Override
    public String toString() {
        return "First Name Count = " + firstNameCount + 
               "\n Last Name Count = " + lastNameCount;
    }
    
    private PeopleAnalytics() {
        
    }
    
    public static PeopleAnalytics getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PeopleAnalytics();
        }
        return INSTANCE;
    }
    
    
}
