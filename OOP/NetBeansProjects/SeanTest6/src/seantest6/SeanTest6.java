package seantest6;

import java.util.ArrayList;
import java.util.List;

public class SeanTest6 {

    public static void main(String[] args) {
        String[] stringArray = new String[5];
        
        for(int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "Element " + (i + 1);
        }
        
        for(int i = 0; i < stringArray.length; i++) {
            System.out.println(stringArray[i]);
        }
        
        List<String> stringList = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            stringList.add("Element " + (i + 1));
        }
        
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }
    
}
