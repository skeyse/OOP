package maptest;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<Integer, Person> map = new HashMap<>();
        
        //Person person = new Person();
        for(int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setFirstName("First Name " + i);
            person.setLastName("Last Name " + i);
            map.put(i, person);
            System.out.println(map.get(i));
        }
        
        
        //System.out.println(map.get(1).toString());
    }
    
}
